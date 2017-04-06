package com.tofly.util.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * 自定义 Hibernate 映射类型
 * Created by lenovo on 2017/3/30.
 */
public class EnumType<T extends Enum<T>> implements UserType {
    //生成的数据库数据类型
    private static final int[] SQL_TYPES = {Types.VARCHAR};
    //Class
    private Class<T> clazz = null;
    //构造方法
    public EnumType(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public int[] sqlTypes() {
        return SQL_TYPES;
    }

    @Override
    public Class returnedClass() {
        return clazz;
    }

    @Override
    public boolean equals(Object arg0, Object arg1) throws HibernateException {
        if (arg0 == arg1)
            return true;
        if ((null == arg0) || (null == arg1))
            return true;
        return arg0.equals(arg1);
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return o.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, Object o) throws HibernateException, SQLException {
        try {
            String string = resultSet.getString(strings[0]).toUpperCase();
            return Enum.valueOf(clazz, string);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i)
            throws HibernateException, SQLException {
        if (null == o) {
            preparedStatement.setNull(i, Types.VARCHAR);
        } else {
            // 将枚举类型转化成字符串
            preparedStatement.setString(i, ((Enum) o).name());
        }
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        return o;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object o) throws HibernateException {
        return (Serializable) o;
    }

    @Override
    public Object assemble(Serializable serializable, Object o) throws HibernateException {
        return serializable;
    }

    @Override
    public Object replace(Object o, Object o1, Object o2) throws HibernateException {
        return o;
    }
}
