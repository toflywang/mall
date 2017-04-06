package com.tofly.util.hibernate;

import com.tofly.model.PaymentWay;

/**
 * 支付方式 Hibernate 映射类型
 * Created by lenovo on 2017/3/30.
 */
public class PaymentWayType extends EnumType<PaymentWay>{
    public PaymentWayType() {
        super(PaymentWay.class);
    }
}
