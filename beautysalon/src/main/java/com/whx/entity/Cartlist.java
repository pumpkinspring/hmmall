package com.whx.entity;

import java.io.Serializable;

/**
 * 购物车表 (Cartlist)实体类
 *
 * @author makejava
 * @since 2022-06-21 11:33:39
 */
public class Cartlist implements Serializable {
    private static final long serialVersionUID = 577375633095512603L;
    /**
     * 物理主键
     */
    private Integer id;
    /**
     * 逻辑主键
     */
    private Long cartId;
    /**
     * 关联的用户ID
     */
    private Long userId;
    /**
     * 商品编号
     */
    private Long goodsId;
    /**
     * 商品数量
     */
    private Integer goodsNum;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

}

