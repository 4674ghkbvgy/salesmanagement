package com.dgut.entity;

public class PurchaseListItem {
    private Integer purchaseListId;
    private Integer goodsId;
    private Integer quantity;



    private Double subtotal;

    public PurchaseListItem() {}

    public PurchaseListItem(Integer purchaseListId, Integer goodsId, Integer quantity) {
        this.purchaseListId = purchaseListId;
        this.goodsId = goodsId;
        this.quantity = quantity;
    }

    public Integer getPurchaseListId() {
        return purchaseListId;
    }

    public void setPurchaseListId(Integer purchaseListId) {
        this.purchaseListId = purchaseListId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setGoods(Goods goods) {
    }


    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
