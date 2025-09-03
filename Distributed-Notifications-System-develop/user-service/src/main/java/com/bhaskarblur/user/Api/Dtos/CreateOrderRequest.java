package com.bhaskarblur.user.Api.Dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public class CreateOrderRequest {

    @NotNull(message = "User ID is required")
    private String user_id;

    @NotNull(message = "Store ID is required")
    private String store_id;

    @NotEmpty(message = "Items list cannot be empty")
    @Valid
    private List<Item> items;

    // Getters and Setters

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    // Inner Item class to represent items in the order
    public static class Item {

        @NotNull(message = "Item ID is required")
        private String id;

        @NotNull(message = "Quantity is required")
        private Integer quantity;

        @NotNull(message = "Price per item is required")
        private Double price_per_item;

        // Getters and Setters

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

        public Double getPrice_per_item() {
            return price_per_item;
        }

        public void setPrice_per_item(Double price_per_item) {
            this.price_per_item = price_per_item;
        }
    }
}
