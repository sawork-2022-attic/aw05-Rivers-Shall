package com.micropos.products.repository;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.micropos.products.model.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDParser {

    @SentinelResource(fallback = "parseJDFallback")
    @Cacheable(value = "JD", key = "#keyword")
    public List<Product> parseJD(String keyword) throws IOException {
        String url = "https://search.jd.com/Search?keyword=" + keyword;
        Document document = Jsoup.parse(new URL(url), 10000);
        Element element = document.getElementById("J_goodsList");
        Elements elements = element.getElementsByTag("li");
        List<Product> list = new ArrayList<>();

        for (Element el : elements) {
            String id = el.attr("data-spu");
            String img = "https:".concat(el.getElementsByTag("img").eq(0).attr("data-lazy-img"));
            String price = el.getElementsByAttribute("data-price").text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            if (title.indexOf("，") >= 0)
                title = title.substring(0, title.indexOf("，"));

            Product product = new Product(id, title, Double.parseDouble(price), img);
            list.add(product);
        }
        return list;
    }

    public List<Product> parseJDFallback(String keyword) {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("1", "product name", 0, "product img"));
        return products;
    }
}
