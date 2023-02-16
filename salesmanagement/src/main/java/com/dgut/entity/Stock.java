package com.dgut.entity;

import java.io.Serial;
import java.io.Serializable;

public class Stock implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int id;
    private int productId;
    private int quantity;

    public Stock() {
    }

    public Stock(int id, int productId, int quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
