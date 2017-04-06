package test;

import com.tofly.dao.user.CustomerDaoImpl;
import com.tofly.model.user.Customer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.List;

/**
 * Created by lenovo on 2017/4/6.
 */

public class DatabaseTest {
    @Test
    public void getCustomer() {
        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        Customer customer = customerDao.get(1);
            System.out.println(customer.getUsername());
            System.out.println(customer.getPassword());
            System.out.println(customer.getRealname());
            System.out.println(customer.getAddress());
            System.out.println(customer.getEmail());
            System.out.println(customer.getMobile());
            System.out.println("--------------------------");

    }

}
