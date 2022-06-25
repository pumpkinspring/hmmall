package com.whx.controller;

import com.whx.entity.Goods;
import com.whx.service.GoodsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *  (Goods)表控制层
 *
 * @author makejava
 * @since 2022-06-21 15:12:11
 */
@RestController
@RequestMapping("goods")
public class GoodsController {
    /**
     * 服务对象
     */
    @Resource
    private GoodsService goodsService;

    /**
     * 分页查询
     *
     * @param goods 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Goods>> queryByPage(Goods goods, PageRequest pageRequest) {
        return ResponseEntity.ok(this.goodsService.queryByPage(goods, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Goods> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.goodsService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param goods 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public ResponseEntity<Goods> add(Goods goods) {

        System.out.println("获取数据:"+goods.toString());
        return ResponseEntity.ok(this.goodsService.insert(goods));
    }

    /**
     * 编辑数据
     *
     * @param goods 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Goods> edit(Goods goods) {
        return ResponseEntity.ok(this.goodsService.update(goods));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.goodsService.deleteById(id));
    }

}

