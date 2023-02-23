package com.dgut.entity;

public class Delivery{
    private int Id;
    private int purchaseListId;
    private int goodsId;
    private int quantity;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getPurchaseListId() {
        return purchaseListId;
    }

    public void setPurchaseListId(int purchaseListId) {
        this.purchaseListId = purchaseListId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
