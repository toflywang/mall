package com.tofly.dao.user;

import com.tofly.dao.BaseDao;
import com.tofly.model.user.Customer;

/**
 * Created by lenovo on 2017/3/30.
 */
public interface CustomerDao extends BaseDao<Customer> {

    public Customer login(String username, String password);

    public boolean isUnique(String username);
}
