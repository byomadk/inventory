package com.example.inventory.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class OrderEntity {

    @Id
    @Column(name = "ORDER_NO")
    private String orderNo;
    @ManyToOne
    @JoinColumn(name = "ITEM_ID", referencedColumnName = "ID")
    private ItemEntity item;
    @Column(name = "QTY")
    private long quantity;
    @Column(name = "PRICE")
    private BigDecimal price;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
