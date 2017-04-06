package com.tofly.model.user;

import java.io.Serializable;

/**
 * 管理员
 * @author Wang Hefei
 * Created by lenovo on 2017/3/25.
 */
public class User implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer id; //管理员编号
    private String username; //管理员名
    private String password; //密码

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
}
