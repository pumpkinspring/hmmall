package com.whx.controller;

import com.whx.config.ResponseData;
import com.whx.entity.Orders;
import com.whx.entity.ordersVO;
import com.whx.service.OrdersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *  (Orders)表控制层
 *
 * @author makejava
 * @since 2022-06-21 11:34:16
 */
@RestController
@RequestMapping("orders")
public class OrdersController {
    /**
     * 服务对象
     */
    @Resource
    private OrdersService ordersService;

    /**
     * 分页查询
     *
     * @param orders 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Orders>> queryByPage(Orders orders, PageRequest pageRequest) {
        return ResponseEntity.ok(this.ordersService.queryByPage(orders, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Orders> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.ordersService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param orders 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Orders> add(Orders orders) {
        return ResponseEntity.ok(this.ordersService.insert(orders));
    }

    /**
     * 编辑数据
     *
     * @param orders 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Orders> edit(Orders orders) {
        return ResponseEntity.ok(this.ordersService.update(orders));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.ordersService.deleteById(id));
    }


    /*
    * 添加订单
    * */
     @PostMapping("/addorder")
    public ResponseData insertOrder(String address,String name,String phone,Long userid){
        return ordersService.insertOrder(address,name,phone,userid);
    }
    /*
    * 查询订单
    * */
    @GetMapping("/queryorder")
    public List<ordersVO> queryOrdersByUserId(Long userId){
        System.out.println(userId);
        return ordersService.queryOrdersByUserId(userId);
    }
    /*
    * 根据订单号查询单个订单
    * */
    @GetMapping("/queryoneorder")
    public ordersVO queryOrderByOrderId(Long orderId){
        System.out.println(orderId);
        return ordersService.queryOrderByOrderId(orderId);
    }
    /*
    * 删除订单
    * */
    @DeleteMapping("/deleteorder")
    public ResponseData deleteOrderByOrderId(Long orderId){
        return ordersService.deleteOrderByOrderId(orderId);
    }

}

