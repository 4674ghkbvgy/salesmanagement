package com.dgut.entity;

import java.sql.Date;

public class PurchaseOrder {
    private int id;
    private int productId;
    private int quantity;
    private Date purchaseDate;



    public PurchaseOrder(int id, int productId, int quantity, Date purchaseDate) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
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

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    // getters and setters
}