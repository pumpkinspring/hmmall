package com.whx.controller;

import com.whx.entity.Address;
import com.whx.service.AddressService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Address)表控制层
 *
 * @author makejava
 * @since 2022-07-02 11:05:47
 */
@RestController
@RequestMapping("address")
public class AddressController {
    /**
     * 服务对象
     */
    @Resource
    private AddressService addressService;

    /**
     * 分页查询
     *
     * @param address 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Address>> queryByPage(Address address, PageRequest pageRequest) {
        return ResponseEntity.ok(this.addressService.queryByPage(address, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Address> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.addressService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param address 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Address> add(Address address) {
        return ResponseEntity.ok(this.addressService.insert(address));
    }

    /**
     * 编辑数据
     *
     * @param address 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Address> edit(Address address) {
        return ResponseEntity.ok(this.addressService.update(address));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.addressService.deleteById(id));
    }
    /*
    *
    * 根据userid查询用户地址
    * */
    @GetMapping("/queryByUserId")
    public List<Address> queryAddressByUserId(Long userId){
        System.out.println(userId);
        return addressService.queryAddressByUserId(userId);
    }

}

