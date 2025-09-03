package com.bhaskarblur.order.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Document(collection = "orders")
public class OrderModel {

    @Id
    private String id;

    @Field("user_id")
    private String userId;

    @Field("store_id")
    private String storeId;

    @Field("items")
    private List<Item> items;

    @Field("created_at")
    private Date createdAt;

    @Field("status")
    private String status;

    public OrderModel() {}
    // Constructor for creating new orders
    public OrderModel(String userId, String storeId, List<Item> items, Date createdAt, String status) {
        this.userId = userId;
        this.storeId = storeId;
        this.items = items;
        this.createdAt = createdAt != null ? createdAt : new Date();
        this.status = status;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Inner Item class to represent each item in the order
    public static class Item {

        @Field("id")
        private String id;

        @Field("quantity")
        private Integer quantity;

        @Field("price_per_item")
        private Double pricePerItem;

        // Constructors, Getters, and Setters
        public Item(String id, Integer quantity, Double pricePerItem) {
            this.id = id;
            this.quantity = quantity;
            this.pricePerItem = pricePerItem;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public Double getPricePerItem() {
            return pricePerItem;
        }

        public void setPricePerItem(Double pricePerItem) {
            this.pricePerItem = pricePerItem;
        }
    }
}
