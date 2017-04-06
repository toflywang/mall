package com.tofly.util.hibernate;

import com.tofly.model.OrderState;

/**
 * 订单状态 Hibernate 映射类型
 * @author Wang Hefei
 * Created by lenovo on 2017/3/30.
 */
public class OrderStateType extends EnumType<OrderState> {
    public OrderStateType() {
        super(OrderState.class);
    }
}
