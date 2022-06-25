package com.whx.entity;

import java.util.Date;
import java.io.Serializable;

/**
 *  (Orders)实体类
 *
 * @author makejava
 * @since 2022-06-21 11:34:16
 */
public class Orders implements Serializable {
    private static final long serialVersionUID = -39447291671243044L;
    /**
     * 物理主键
     */
    private Integer id;
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
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品价格
     */
    private Double goodsPrice;
    /**
     * 商品数量
     */
    private Integer goodsNum;
    /*
    * 用户ID
    * */
    private Long userId;
    /*
    * 商品图片
    * */
    private String goodsSmallLogo;

    public String getGoodsSmallLogo() {
        return goodsSmallLogo;
    }

    public void setGoodsSmallLogo(String goodsSmallLogo) {
        this.goodsSmallLogo = goodsSmallLogo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", orderTime=" + orderTime +
                ", orderStatus=" + orderStatus +
                ", orderAddress='" + orderAddress + '\'' +
                ", orderPhone='" + orderPhone + '\'' +
                ", orderName='" + orderName + '\'' +
                ", orderPrice=" + orderPrice +
                ", goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", goodsNum=" + goodsNum +
                ", userId=" + userId +
                '}';
    }
}

