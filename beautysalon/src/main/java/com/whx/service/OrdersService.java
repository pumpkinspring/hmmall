package com.whx.service;

import com.whx.config.ResponseData;
import com.whx.entity.Orders;
import com.whx.entity.ordersVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 *  (Orders)表服务接口
 *
 * @author makejava
 * @since 2022-06-21 11:34:16
 */
public interface OrdersService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Orders queryById(Integer id);

    /**
     * 分页查询
     *
     * @param orders 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Orders> queryByPage(Orders orders, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    Orders insert(Orders orders);

    /**
     * 修改数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    Orders update(Orders orders);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);
/*
* 添加订单
* */
    public ResponseData insertOrder(String address,String name,String phone,Long userid);

/*
* 查询订单
* */
public List<ordersVO> queryOrdersByUserId(Long userId);
/*
* 查询单个订单
* */
public  ordersVO queryOrderByOrderId(Long orderId);
/*
* 删除订单
* */
public ResponseData deleteOrderByOrderId(Long orderId);

}
