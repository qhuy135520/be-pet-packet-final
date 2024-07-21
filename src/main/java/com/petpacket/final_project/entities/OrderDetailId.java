package com.petpacket.final_project.entities;


import java.io.Serializable;
import java.util.Objects;

public class OrderDetailId implements Serializable {
    private Long orderID;
    private Integer productID;

    public OrderDetailId() {
    }

    public OrderDetailId(Long orderID, Integer productID) {
        this.orderID = orderID;
        this.productID = productID;
    }

    // Getters and Setters
    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailId that = (OrderDetailId) o;
        return Objects.equals(orderID, that.orderID) && Objects.equals(productID, that.productID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, productID);
    }
}
