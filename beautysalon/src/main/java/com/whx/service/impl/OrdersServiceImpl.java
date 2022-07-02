package com.whx.service.impl;

import com.whx.config.GetDateUtil;
import com.whx.config.ResponseData;
import com.whx.config.StringUtil;
import com.whx.dao.CartlistDao;
import com.whx.dao.GoodsDao;
import com.whx.entity.*;
import com.whx.dao.OrdersDao;
import com.whx.service.OrdersService;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.*;

/**
 *  (Orders)表服务实现类
 *
 * @author makejava
 * @since 2022-06-21 11:34:16
 */
@Transactional(rollbackFor =Exception.class)
@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {
    @Resource
    private OrdersDao ordersDao;
    @Resource
    private CartlistDao cartlistDao;
    @Resource
    private GoodsDao goodsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Orders queryById(Integer id) {
        return this.ordersDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param orders 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Orders> queryByPage(Orders orders, PageRequest pageRequest) {
        long total = this.ordersDao.count(orders);
        return new PageImpl<>(this.ordersDao.queryAllByLimit(orders, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    @Override
    public Orders insert(Orders orders) {
        this.ordersDao.insert(orders);
        return orders;
    }

    /**
     * 修改数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    @Override
    public Orders update(Orders orders) {
        this.ordersDao.update(orders);
        return this.queryById(orders.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.ordersDao.deleteById(id) > 0;
    }
    /*
    * 修改商品数量
    * */
/*
*
* */
    @Override
    public ResponseData insertOrder(String address,String name,String phone,Long userid) {
            Long orderId= GetDateUtil.getCurrentTimeForId();
            Date orderTime=new Date();
            Integer orderStatus=1;
            String orderAddress=address;
            String orderName=name;
            String orderPhone=phone;
            try {
                //获取当前用户的购物车中的信息
                List<Cartlist> cartlist = cartlistDao.queryCartListByUserId(userid);
//            List<Goods> goods=new ArrayList<>();
                for (int i = 0; i < cartlist.size(); i++) {
                    //通过商品ID查询商品信息
                    Goods good = goodsDao.queryByGoodsId(cartlist.get(i).getGoodsId());
                    //封装一个Orders对象 向订单表中插入
                    /*
                     * id order_id order_time order_status order_address order_phone
                     * order_name order_price goods_id goods_name goods_price goods_num user_id
                     * */
                    Orders order = new Orders();
                    order.setOrderId(orderId);
                    order.setOrderTime(orderTime);
                    order.setOrderStatus(orderStatus);
                    order.setOrderAddress(orderAddress);
                    order.setOrderPhone(orderPhone);
                    order.setOrderName(orderName);
                    //当前条目的商品总价=商品数量*商品单价
                    order.setOrderPrice(good.getGoodsPrice() * cartlist.get(i).getGoodsNum());
                    order.setGoodsId(cartlist.get(i).getGoodsId());
                    order.setGoodsName(good.getGoodsName());
                    order.setGoodsPrice(good.getGoodsPrice());
                    order.setGoodsNum(cartlist.get(i).getGoodsNum());
                    order.setUserId(userid);
                    order.setGoodsSmallLogo(good.getGoodsSmallLogo());
                    //封装完毕 向订单表中插入
                    ordersDao.insert(order);
                    //修改商品表中商品的数量
                    good.setGoodsNumber(good.getGoodsNumber()-order.getGoodsNum());
                    goodsDao.update(good);
                }

                //该用户的购物车中的商品已经全部生成订单 删除该用户购物车信息
                cartlistDao.deleteByUserId(userid);


                return new ResponseData("0", "添加订单成功");
            }catch (Exception e){
                System.out.println(e);
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return  new ResponseData("9999","网络异常");
            }
    }
    /*
    *
    * 查询订单
    * */

    @Override
    public List<ordersVO> queryOrdersByUserId(Long userId) {
        //查询用户的所有订单信息 存放在list中
        List<Orders> list=ordersDao.queryOrdersByUserId(userId);
        System.out.println("userid:"+userId+"list:");
        for(Orders o:list){
            System.out.println(o.toString());
        }
        //将每个VO的DTO列表封装到一个map中，key：订单ID，value：订单的商品信息
        Map<Long, List<ordersDTO>> map=new HashMap<>();
        for(Orders o:list){
            ordersDTO dto=new ordersDTO(o.getGoodsId(),o.getGoodsName(),o.getGoodsPrice(),o.getGoodsNum(),o.getGoodsSmallLogo());
//            id  名称 价格 数量
            List<ordersDTO> ls=map.getOrDefault(o.getOrderId(),new ArrayList<ordersDTO>());
            ls.add(dto);
            map.put(o.getOrderId(),ls);
        }
             //封装VO
        List<ordersVO> res=new ArrayList<>();
        for(Map.Entry<Long,List<ordersDTO>> entry:map.entrySet()){
             ordersVO vo=new ordersVO();
             vo.setList(entry.getValue());
             vo.setOrderId(entry.getKey());
             int index=0;
             while(list.get(index).getOrderId()!=entry.getKey()) index++;
             vo.setOrderAddress(list.get(index).getOrderAddress());
             vo.setOrderName(list.get(index).getOrderName());
             vo.setOrderPhone(list.get(index).getOrderPhone());
             vo.setOrderStatus(list.get(index).getOrderStatus());
             vo.setOrderTime(list.get(index).getOrderTime());
             //订单总价是多条order数据的price 的和
             Double price=0.00;
             for(Orders o:list){
                 if(o.getOrderId()==entry.getKey()){
                     price+=o.getOrderPrice();
                 }
             }
             vo.setOrderPrice(price);
             res.add(vo);
        }
        System.out.println("订单："+res.size());
        return res;
    }

    /*
    * 查询单个订单
    * */

    @Override
    public ordersVO queryOrderByOrderId(Long orderId) {
        List<Orders> list=ordersDao.queryOrderByOrderId(orderId);
        List<ordersDTO> dtolist=new ArrayList<>();
        Double price=0.00;
        for(Orders o:list){
            ordersDTO dto=new ordersDTO();
            dto.setGoodsId(o.getGoodsId());
            dto.setGoodsName(o.getGoodsName());
            dto.setGoodsNum(o.getGoodsNum());
            dto.setGoodsPrice(o.getGoodsPrice());
            dto.setGoodsSmallLogo(o.getGoodsSmallLogo());
            dtolist.add(dto);
            price+=o.getOrderPrice();
        }
        ordersVO vo=new ordersVO();
        vo.setOrderId(list.get(0).getOrderId());
        vo.setOrderTime(list.get(0).getOrderTime());
        vo.setOrderStatus(list.get(0).getOrderStatus());
        vo.setOrderPhone(list.get(0).getOrderPhone());
        vo.setOrderName(list.get(0).getOrderName());
        vo.setOrderAddress(list.get(0).getOrderAddress());
        vo.setOrderPrice(price);
        vo.setList(dtolist);
        return vo;
    }

    /*
    * 删除订单
    * */

    @Override
    public ResponseData deleteOrderByOrderId(Long orderId) {
        if(StringUtil.isNull(orderId))
            return new ResponseData("1001","订单号为空");
        try {
            int i=ordersDao.deleteByOrderId(orderId);
            if(i!=0){
                return new ResponseData("0","删除成功");
            }else{
                return new ResponseData("9998","订单不存在");
            }

        }catch (Exception e){
            System.out.println(e);
            return new ResponseData("9999","网络异常");
        }



    }
}
