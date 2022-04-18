package com.micropos.carts.model;

import lombok.Data;
import org.springframework.context.annotation.Primary;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Cart implements Serializable {
    @ElementCollection
    private List<Item> items;
    @Id
    @GeneratedValue
    private Long cartId;

    public Long getCartId() {
        return cartId;
    }
}
