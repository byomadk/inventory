package com.example.inventory.model.entity;

import jakarta.persistence.*;

@Entity
public class InventoryEntity {

    @Id
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ITEM_ID", referencedColumnName = "ID")
    private ItemEntity item;
    @Column(name = "QTY")
    private Long quantity;
    @Column(name = "TYPE")
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
