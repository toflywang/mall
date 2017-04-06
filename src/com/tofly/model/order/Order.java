package com.tofly.model.order;

import com.tofly.model.OrderState;
import com.tofly.model.PaymentWay;
import com.tofly.model.user.Customer;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 订单
 * @author Wang Hefei
 * Created by lenovo on 2017/3/25.
 */
public class Order implements Serializable{
    private static final long serialVersionUID = 1L;

    private String orderId; //订单编号(手动分配)
    private String name; //收货人姓名
    private String address; //收货人地址
    private String mobile; //收获人手机
    private Float totolPrice; //总额
    private Date createTime = new Date(); //创建时间
    private PaymentWay paymentWay; //支付方式
    private OrderState orderState; //订单状态
    private Customer customer; //所属用户
    private Set<OrderItem> orderItems; //所买商品

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Float getTotolPrice() {
        return totolPrice;
    }

    public void setTotolPrice(Float totolPrice) {
        this.totolPrice = totolPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public PaymentWay getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(PaymentWay paymentWay) {
        this.paymentWay = paymentWay;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
