package com.whx.entity;

public class cartListDTO {

//    商品id
    private Long goodsId;


    /**
     * 商品名称
     */
    private String goodsName;
    //商品价格
    private Double goodsPrice;
    /**
     * goods_small_logo
     */
    private String goodsSmallLogo;
    // 商品数量
    private int goodsNum;

    public cartListDTO() {

    }

    @Override
    public String toString() {
        return "cartListDTO{" +
                "goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", goodsSmallLogo='" + goodsSmallLogo + '\'' +
                ", goodsNum=" + goodsNum +
                '}';
    }

    public cartListDTO(Long goodsId, String goodsName, Double goodsPrice, String goodsSmallLogo, int goodsNum) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.goodsSmallLogo = goodsSmallLogo;
        this.goodsNum = goodsNum;
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

    public String getGoodsSmallLogo() {
        return goodsSmallLogo;
    }

    public void setGoodsSmallLogo(String goodsSmallLogo) {
        this.goodsSmallLogo = goodsSmallLogo;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }
}
