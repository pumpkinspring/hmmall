package com.whx.service.impl;

import com.whx.config.GetDateUtil;
import com.whx.config.ResponseData;
import com.whx.config.StringUtil;
import com.whx.dao.GoodsDao;
import com.whx.entity.Cartlist;
import com.whx.dao.CartlistDao;
import com.whx.entity.Goods;
import com.whx.entity.cartListDTO;
import com.whx.entity.cartListVO;
import com.whx.service.CartlistService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车表 (Cartlist)表服务实现类
 *
 * @author makejava
 * @since 2022-06-21 11:33:41
 */
@Service("cartlistService")
public class CartlistServiceImpl implements CartlistService {
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
    public Cartlist queryById(Integer id) {
        return this.cartlistDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param cartlist    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Cartlist> queryByPage(Cartlist cartlist, PageRequest pageRequest) {
        long total = this.cartlistDao.count(cartlist);
        return new PageImpl<>(this.cartlistDao.queryAllByLimit(cartlist, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param cartlist 实例对象
     * @return 实例对象
     */
    @Override
    public Cartlist insert(Cartlist cartlist) {
        this.cartlistDao.insert(cartlist);
        return cartlist;
    }

    /**
     * 修改数据
     *
     * @param cartlist 实例对象
     * @return 实例对象
     */
    @Override
    public Cartlist update(Cartlist cartlist) {
        this.cartlistDao.update(cartlist);
        return this.queryById(cartlist.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.cartlistDao.deleteById(id) > 0;
    }

    @Override
    public cartListVO queryCartListByUserId(Long userid) {
        System.out.println("获取购物测");
        //获取当前用户购物车
        List<Cartlist> cartlist = cartlistDao.queryCartListByUserId(userid);
        for (Cartlist cart : cartlist) {
            System.out.println("获取到cartlist");
            System.out.println(cart);
        }

        List<cartListDTO> dtolist = new ArrayList<>();
        /*
         *  cartListDTO(Long goodsId, String goodsName, Double goodsPrice, String goodsSmallLogo, int goodsNum)
         * */
        //通过商品id获得商品信息 并封装到DTO中
        for (int i = 0; i < cartlist.size(); i++) {
            System.out.println("获取商品信息");
            Long goodsid = cartlist.get(i).getGoodsId();
            Goods good = goodsDao.queryByGoodsId(goodsid);
            System.out.println(good);
            cartListDTO dto = new cartListDTO(goodsid, good.getGoodsName(), good.getGoodsPrice(), good.getGoodsSmallLogo(), cartlist.get(i).getGoodsNum());
            dtolist.add(dto);
        }
        /*
        *    public cartListVO( Long userId, List<cartListDTO> goods) {

        this.userId = userId;
        this.goods = goods;
    }
        * */
        //将dto封装到vo中
        return new cartListVO(userid, dtolist);
    }


    /*
     * 向购物车中添加商品
     * */
    @Override
    public void insertGoodtoCart(Long userid, Long goodsid) {
        Long cartId = GetDateUtil.getCurrentTimeForId();
        try {
            Cartlist cart = cartlistDao.queryCartByUserIdAndGoodsId(userid, goodsid);
            // 如果该用户购物车中已经存在该商品 对商品数量进行累加
            if (cart == null) {
                //用户购物车中没有该商品 插入新的数据

                Cartlist cartlist = new Cartlist();
                cartlist.setUserId(userid);
                cartlist.setGoodsId(goodsid);
                cartlist.setCartId(cartId);
                cartlist.setGoodsNum(1);
                int status = cartlistDao.insert(cartlist);
            } else {
                //用户购物车中有该商品 修改更新数据

                cart.setGoodsNum(cart.getGoodsNum() + 1);
                cart.setCartId(cartId);
                int i = cartlistDao.update(cart);

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /*
     * 修改商品数量
     * */
    @Override
    public ResponseData changeNum(Long userid, Long goodsid, Integer num) {
        try {
            Cartlist cart = cartlistDao.queryCartByUserIdAndGoodsId(userid, goodsid);

            System.out.println("num:" + num);
            if (num != null)
                cart.setGoodsNum(num);

            if (cart != null) {
                int i = cartlistDao.update(cart);
            }
            return new ResponseData("0", "修改成功");
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseData("9999", "网络异常");
        }
    }

    /*
     *
     * 删除单条购物车数据
     * */

    @Override
    public ResponseData deleteCartByGoodsId(Long goodsid, Long userid) {

        try {
            int i = cartlistDao.deleteByGoodsIdAndUserId(goodsid, userid);
            if (i != 0) {
                return new ResponseData("0", "删除成功");
            } else {
                return new ResponseData("9998", "订单不存在");
            }

        } catch (Exception e) {
            System.out.println(e);
            return new ResponseData("9999", "网络异常");
        }
    }
}
