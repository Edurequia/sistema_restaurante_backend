package com.example.dudu.dtos;

import java.util.Objects;

public class OrderItemRequestDTO {
    private Long productId;
    private Integer quantity;

    public OrderItemRequestDTO() {

    }

    public OrderItemRequestDTO(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItemRequestDTO that)) return false;
        return Objects.equals(getProductId(), that.getProductId()) && Objects.equals(getQuantity(), that.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getQuantity());
    }

    @Override
    public String toString() {
        return "OrderItemRequestDTO{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
