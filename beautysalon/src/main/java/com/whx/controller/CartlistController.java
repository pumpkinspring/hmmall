package com.whx.controller;

import com.whx.config.ResponseData;
import com.whx.entity.Cartlist;
import com.whx.entity.cartListVO;
import com.whx.service.CartlistService;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 购物车表 (Cartlist)表控制层
 *
 * @author makejava
 * @since 2022-06-21 11:33:39
 */
@RestController
@RequestMapping("cartlist")
public class CartlistController {
    /**
     * 服务对象
     */
    @Resource
    private CartlistService cartlistService;

    /**
     * 分页查询
     *
     * @param cartlist 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Cartlist>> queryByPage(Cartlist cartlist, PageRequest pageRequest) {
        return ResponseEntity.ok(this.cartlistService.queryByPage(cartlist, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Cartlist> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.cartlistService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param cartlist 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public ResponseEntity<Cartlist> add(Cartlist cartlist) {
        return ResponseEntity.ok(this.cartlistService.insert(cartlist));
    }

    /**
     * 编辑数据
     *
     * @param cartlist 实体
     * @return 编辑结果
     */
    @PutMapping("/update")
    public ResponseEntity<Cartlist> edit(Cartlist cartlist) {
        return ResponseEntity.ok(this.cartlistService.update(cartlist));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.cartlistService.deleteById(id));
    }
/*
*   通过用户id查询购物车
*
* */
@GetMapping("/querycart")
    public cartListVO queryCartListByUserId(Long userid){
        return cartlistService.queryCartListByUserId(userid);
    }

    /*
    * 向购物车中添加商品
    * */

    @PostMapping("/addgoods")
    public void insertGoodtoCart(Long userid,Long goodsid){

        System.out.println(userid);
        System.out.println(goodsid);
        System.out.println("==========================");
        cartlistService.insertGoodtoCart(userid,goodsid);
    }

    /*
    * 修改数量
    * */
    @PutMapping("/changeNum")
    public ResponseData changeNum(Long userid,Long goodsid,Integer num){

        System.out.println(userid);
        System.out.println(goodsid);
        System.out.println("num1:"+num);

        return cartlistService.changeNum(userid,goodsid,num);
    }
    /*
    * 删除单条购物车数据
    * */
    @DeleteMapping("/deletecart")
    public ResponseData deleteCartByGoodsId(Long goodsid,Long userid){
        return cartlistService.deleteCartByGoodsId(goodsid,userid);
    }
}

