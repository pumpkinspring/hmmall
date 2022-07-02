package com.whx.dao;

import com.whx.entity.Cartlist;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 购物车表 (Cartlist)表数据库访问层
 *
 * @author makejava
 * @since 2022-06-21 11:33:39
 */
@Mapper
@Transactional
public interface CartlistDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Cartlist queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param cartlist 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Cartlist> queryAllByLimit(Cartlist cartlist, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param cartlist 查询条件
     * @return 总行数
     */
    long count(Cartlist cartlist);

    /**
     * 新增数据
     *
     * @param cartlist 实例对象
     * @return 影响行数
     */
    int insert(Cartlist cartlist);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Cartlist> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Cartlist> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Cartlist> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Cartlist> entities);

    /**
     * 修改数据
     *
     * @param cartlist 实例对象
     * @return 影响行数
     */
    int update(Cartlist cartlist);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);


/*
*
* 通过userid查询购物车信息
* */
    List<Cartlist> queryCartListByUserId(Long userid);

    /*
    * 向购物车中添加商品
    * */

    int insertGoodtoCart(@Param("userId") Long userId,@Param("goodsId") Long goodsId,@Param("cartId") Long cartId,@Param("goodsNum") int goodsNum);

    /*
     *
     * 通过商品id和用户id查询购物车信息
     * */
    Cartlist queryCartByUserIdAndGoodsId(@Param("userid") Long userid,@Param("goodsid") Long goodsid);
    /*
    * 通过userid查询购物车中的商品编号
    * */
    List<Long> queryGoodsIdByUserId(Long userid);
    /*
     * 通过userid删除数据
     *
     * */
    int deleteByUserId(Long userid);
    /*
    * 删除单条购物车数据
    * */
    int deleteByGoodsIdAndUserId(@Param("goodsid") Long goodsid,@Param("userid") Long userid);
}

