package com.whx.entity;

import java.util.List;

public class cartListVO {

    /**
     * 关联的用户ID
     */
    private Long userId;
   //商品信息
    private List<cartListDTO> goods;

    public cartListVO( Long userId, List<cartListDTO> goods) {

        this.userId = userId;
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "cartListVO{" +

                ", userId=" + userId +
                ", goods=" + goods +
                '}';
    }



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<cartListDTO> getGoods() {
        return goods;
    }

    public void setGoods(List<cartListDTO> goods) {
        this.goods = goods;
    }
}
