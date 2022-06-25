package com.whx.entity;

import java.io.Serializable;

/**
 *  (User)实体类
 *
 * @author makejava
 * @since 2022-06-21 11:34:26
 */
public class User implements Serializable {
    private static final long serialVersionUID = -73658228283545435L;
    /**
     * id
     */
    private Integer id;
    /**
     * user_id
     */
    private Long userId;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 微信用户唯一标识
     */
    private String openid;
    /**
     * 头像路径
     */
    private String imageurl;
    /**
     * sessionkey
     */
    private String sessionkey;
    /**
     * token
     */
    private String token;
    
    private String username;
    
    private String phone;
    
    private String password;


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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getSessionkey() {
        return sessionkey;
    }

    public void setSessionkey(String sessionkey) {
        this.sessionkey = sessionkey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

