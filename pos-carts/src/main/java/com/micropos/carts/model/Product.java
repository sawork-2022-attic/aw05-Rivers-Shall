package com.micropos.carts.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Product implements Serializable {
    private String id;
    private String name;
    private double price;
    private String image;
}
