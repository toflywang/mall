package com.tofly.dao.user;

import com.tofly.dao.BaseDao;
import com.tofly.model.user.User;

/**
 * Created by lenovo on 2017/4/6.
 */
public interface UserDao extends BaseDao<User> {

    public User login(String username, String password);

    public boolean isUnique(String username);
}
