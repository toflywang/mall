package com.tofly.dao;

import com.tofly.model.PageModel;

import java.io.Serializable;
import java.util.Map;

/**
 * 基本数据库操作方法接口
 * @author Wang Hefei
 * Created by lenovo on 2017/3/25.
 */
public interface BaseDao<T> {

    public void save(Object obj); //保存数据

    public void saveOrUpdate(Object obj); //保存或修改数据

    public void Update(Object obj); //修改数据

    public void delete(Serializable ... ids); //删除数据

    public T get(Serializable entityId); //加载实体对象

    public T load(Serializable entityId); //加载实体对象

    public Object uniqueResult(String hql, Object[] queryParams); //使用hql语句操作

    /**
     * 分页操作
     */
    public long getCount(); //获取总信息数

    public PageModel<T> find(int pageNo, int maxResult); //普通分页操作
    public PageModel<T> find(int pageNo, int maxResult, String where, Object[] queryParams); //搜索信息分页方法
    public PageModel<T> find(int pageNo, int maxResult, Map<String, String> orderby); //按指定条件排序分页方法
    public PageModel<T> find(String where, Object[] queryParams, Map<String, String> orderby,
                             int pageNo, int maxResult); //按指定条件分页和排序的分页方法

}
