package com.micropos.carts.model;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class Item implements Serializable {
    private Product product;
    private int quantity;
}
