package com.example.shopappfront.data.models;

import java.math.BigDecimal;

public class OrderedItems implements ApplicationModel {

    private Item item;
    private int quantity;
    private Integer orderId;
    private BigDecimal totalPrice;

    public OrderedItems(Item item, int quantity, Integer orderId) {
        this.item = item;
        this.quantity = quantity;
        this.orderId = orderId;
        totalPrice = item.getItemFinalPrice().multiply(new BigDecimal(quantity));
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        totalPrice = item.getItemFinalPrice().multiply(new BigDecimal(quantity));
    }

    public BigDecimal getTotalPrice() {
        if (totalPrice == null)
            totalPrice = item.getItemFinalPrice().multiply(new BigDecimal(quantity));
        return totalPrice;
    }

    public Integer getOrderId() {
        return orderId;
    }
}
