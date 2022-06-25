package com.whx.entity;

public class ordersDTO {
    //    商品id
    private Long goodsId;


    /**
     * 商品名称
     */
    private String goodsName;
    //商品价格
    private Double goodsPrice;
    // 商品数量
    private int goodsNum;

//  商品图片
private String goodsSmallLogo;

    public ordersDTO(Long goodsId, String goodsName, Double goodsPrice, int goodsNum, String goodsSmallLogo) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.goodsNum = goodsNum;
        this.goodsSmallLogo = goodsSmallLogo;
    }
    public ordersDTO(){}

    @Override
    public String toString() {
        return "ordersDTO{" +
                "goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", goodsNum=" + goodsNum +
                '}';
    }

    public String getGoodsSmallLogo() {
        return goodsSmallLogo;
    }

    public void setGoodsSmallLogo(String goodsSmallLogo) {
        this.goodsSmallLogo = goodsSmallLogo;
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

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }
}
