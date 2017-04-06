package com.tofly.util.hibernate;

import com.tofly.model.Sex;

/**
 * 使用性别 Hibernate 映射类型
 * Created by lenovo on 2017/3/28.
 */
public class SexType extends EnumType<Sex> {
    public SexType() {
        super(Sex.class);
    }
}
