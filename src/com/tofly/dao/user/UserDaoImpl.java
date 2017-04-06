package com.tofly.dao.user;

import com.tofly.dao.DaoSupport;
import com.tofly.model.user.User;

/**
 * Created by lenovo on 2017/4/6.
 */
public class UserDaoImpl extends DaoSupport<User> implements UserDao {
    @Override
    public User login(String username, String password) {
        return null;
    }

    @Override
    public boolean isUnique(String username) {
        return false;
    }
}
