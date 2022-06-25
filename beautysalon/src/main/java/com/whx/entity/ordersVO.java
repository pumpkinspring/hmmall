package com.whx.entity;

import java.util.Date;
import java.util.List;

public class ordersVO {
    /**
     * 订单号
     */
    private Long orderId;
    /**
     * 订单时间
     */
    private Date orderTime;
    /**
     * 订单状态
     */
    private Integer orderStatus;
    /**
     * 订单地址
     */
    private String orderAddress;
    /**
     * 订单电话
     */
    private String orderPhone;
    /**
     * 订单姓名
     */
    private String orderName;
    /**
     * 订单金额
     */
    private Double orderPrice;
    /*
    * 商品信息
    * */
    private List<ordersDTO> list;

    public ordersVO(){}
    public ordersVO(Long orderId, Date orderTime, Integer orderStatus, String orderAddress, String orderPhone, String orderName, Double orderPrice, List<ordersDTO> list) {
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
        this.orderAddress = orderAddress;
        this.orderPhone = orderPhone;
        this.orderName = orderName;
        this.orderPrice = orderPrice;
        this.list = list;
    }

    @Override
    public String toString() {
        return "ordersVO{" +
                "orderId=" + orderId +
                ", orderTime=" + orderTime +
                ", orderStatus=" + orderStatus +
                ", orderAddress='" + orderAddress + '\'' +
                ", orderPhone='" + orderPhone + '\'' +
                ", orderName='" + orderName + '\'' +
                ", orderPrice=" + orderPrice +
                ", list=" + list +
                '}';
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public List<ordersDTO> getList() {
        return list;
    }

    public void setList(List<ordersDTO> list) {
        this.list = list;
    }
}
