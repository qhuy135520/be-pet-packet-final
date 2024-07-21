package com.petpacket.final_project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"OrderDetail\"", schema = "public")
@IdClass(OrderDetailId.class)
public class OrderDetail {

    @Id
    private Long orderID;

    @Id
    private Integer productID;

    private Long unitPrice;
    private Integer quantity;
    private Integer discount;

    @ManyToOne
    @JoinColumn(name = "OrderID", insertable = false, updatable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "ProductID", insertable = false, updatable = false)
    private ServiceStore serviceStore;

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

    public Long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ServiceStore getServiceStore() {
        return serviceStore;
    }

    public void setServiceStore(ServiceStore serviceStore) {
        this.serviceStore = serviceStore;
    }
}
