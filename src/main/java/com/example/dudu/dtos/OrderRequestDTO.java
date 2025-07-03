package com.example.dudu.dtos;

import java.util.List;
import java.util.Objects;

public class OrderRequestDTO {
    private List<OrderItemRequestDTO> items;

    public OrderRequestDTO() {

    }

    public OrderRequestDTO(List<OrderItemRequestDTO> items) {
        this.items = items;
    }

    public List<OrderItemRequestDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequestDTO> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderRequestDTO that)) return false;
        return Objects.equals(getItems(), that.getItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItems());
    }

    @Override
    public String toString() {
        return "OrderRequestDTO{" +
                "items=" + items +
                '}';
    }
}
