package com.tofly.model.user;

import java.io.Serializable;

/**
 * 消费者用户
 * @author Wang Hefei
 * Created by lenovo on 2017/3/25.
 */
public class Customer implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer id; //消费者用户编号
    private String username; //用户名
    private String password; //用户密码
    private String realname; //用户真实姓名
    private String address; //地址
    private String email; //电子邮箱
    private String mobile; //手机号

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
