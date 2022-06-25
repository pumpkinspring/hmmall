package com.whx.service;

import com.whx.config.ResponseData;
import com.whx.entity.Cartlist;
import com.whx.entity.cartListVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 购物车表 (Cartlist)表服务接口
 *
 * @author makejava
 * @since 2022-06-21 11:33:40
 */
public interface CartlistService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Cartlist queryById(Integer id);

    /**
     * 分页查询
     *
     * @param cartlist 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Cartlist> queryByPage(Cartlist cartlist, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param cartlist 实例对象
     * @return 实例对象
     */
    Cartlist insert(Cartlist cartlist);

    /**
     * 修改数据
     *
     * @param cartlist 实例对象
     * @return 实例对象
     */
    Cartlist update(Cartlist cartlist);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);
/*
* 通过userid查询购物车信息
* */
    cartListVO queryCartListByUserId(Long userid);
/*
*
* 向购物车添加商品
* */
  void insertGoodtoCart(Long userid,Long goodsid);
/*
* 修改商品数量
* */
 ResponseData changeNum(Long userid,Long goodsid,Integer num);
 /*
 * 删除单条购物车数据
 * */
 ResponseData deleteCartByGoodsId(Long goodsid,Long userid);

}
