package com.example.dudu.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class OrderDTO {
    private Long id;
    private LocalDateTime orderDate;
    private String status;
    private Double total;
    private List<OrderItemDTO> items;

    public OrderDTO() {

    }

    public OrderDTO(Long id, LocalDateTime orderDate, String status, Double total, List<OrderItemDTO> items) {
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
        this.total = total;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDTO orderDTO)) return false;
        return Objects.equals(getId(), orderDTO.getId()) && Objects.equals(getOrderDate(), orderDTO.getOrderDate()) && Objects.equals(getStatus(), orderDTO.getStatus()) && Objects.equals(getTotal(), orderDTO.getTotal()) && Objects.equals(getItems(), orderDTO.getItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOrderDate(), getStatus(), getTotal(), getItems());
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                ", total=" + total +
                ", items=" + items +
                '}';
    }
}
