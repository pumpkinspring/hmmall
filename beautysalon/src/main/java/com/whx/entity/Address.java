package com.whx.entity;

import java.io.Serializable;

/**
 * (Address)实体类
 *
 * @author makejava
 * @since 2022-07-02 11:05:47
 */
public class Address implements Serializable {
    private static final long serialVersionUID = 438269359876471431L;
    /**
     * 物理主键
     */
    private Integer id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户地址
     */
    private String address;
    /**
     * 收件人姓名
     */
    private String name;
    /**
     * 收件人电话
     */
    private String phone;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}

