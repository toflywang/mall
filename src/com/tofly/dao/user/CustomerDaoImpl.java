package com.tofly.dao.user;

import com.tofly.dao.DaoSupport;
import com.tofly.model.user.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lenovo on 2017/3/30.
 */
@Repository("customerDao")
@Transactional
public class CustomerDaoImpl extends DaoSupport<Customer> implements CustomerDao {
    @Override
    @Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly = true)
    public Customer login(String username, String password) {
        String where = "where username=? and password=?";
        Object[] queryParms = {username, password};
        List<Customer> list = find(-1, -1, where, queryParms).getList();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly = true)
    public boolean isUnique(String username) {
        List list = getTemplate().find("from Customer where username = ?", username);
        if (list != null && list.size() > 0) {
            return false;
        }
        return true;
    }
}
