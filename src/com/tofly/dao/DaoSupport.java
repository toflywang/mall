package com.tofly.dao;

import com.tofly.model.PageModel;
import com.tofly.util.GenericsUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Dao 支持类
 * @author Wang Hefei
 * @param <T>
 * Created by lenovo on 2017/3/25.
 */
@Transactional
@SuppressWarnings("unchecked")
public class DaoSupport<T> implements BaseDao<T> {

    //泛型的类型
    protected Class<T> entityClass = GenericsUtils.getGenericType(this.getClass());

    // Hibernate 模板
    @Autowired
    protected HibernateTemplate template;

    public HibernateTemplate getTemplate() {
        return template;
    }

    /**
     * 利用 save() 方法保存对象的详细信息
     */
    @Override
    public void save(Object obj) {
        getTemplate().save(obj);
    }

    @Override
    public void saveOrUpdate(Object obj) {
        getTemplate().saveOrUpdate(obj);
    }

    /**
     * 利用 save() 方法保存对象的详细信息
     */
    @Override
    public void Update(Object obj) {
        getTemplate().update(obj);
    }

    @Override
    public void delete(Serializable ... ids) {
        for (Serializable id : ids) {
            T t = (T) getTemplate().load(this.entityClass, id);
            getTemplate().delete(t);
        }
    }

    /**
     * 利用 get() 方法加载对象， 获取对象的详细信息
     */
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public T get(Serializable entityId) {
        return (T) getTemplate().get(this.entityClass, entityId);
    }

    /**
     * 利用 load() 方法加载对象， 获取对象的详细信息
     */
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public T load(Serializable entityId) {
        return (T) getTemplate().load(this.entityClass, entityId);
    }

    /**
     * 利用 hql 语句查找单条信息
     */
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Object uniqueResult(String hql, Object[] queryParams) {
        return getTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(hql); //执行查询
                setQueryParams(query, queryParams); //设置查询参数
                return query.uniqueResult();
            }
        });
    }

    /**
     * 获取指定对象的信息条数
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public long getCount() {
        String hql = "select count(*) from " + GenericsUtils.getGenericName(this.entityClass);
        return (Long) uniqueResult(hql, null);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public PageModel<T> find(int pageNo, int maxResult) {
        return find(null, null, null, pageNo, maxResult);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public PageModel<T> find(int pageNo, int maxResult, String where, Object[] queryParams) {
        return find(where, queryParams, null, pageNo, maxResult);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public PageModel<T> find(int pageNo, int maxResult, Map<String, String> orderby) {
        return find(null, null, orderby, pageNo, maxResult);
    }

    /**
     * 分页查询
     * @param where 查询条件
     * @param queryParams hql参数
     * @param orderby 排序
     * @param pageNo 第几页
     * @param maxResult 返回记录数量
     * @return PageModel
     */
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public PageModel<T> find(final String where, final Object[] queryParams, final Map<String, String> orderby,
                             final int pageNo, final int maxResult) {
        final PageModel<T> pageModel = new PageModel<T>(); //实例化分页对象
        pageModel.setPageNo(pageNo); //设置当前页数
        pageModel.setPageSize(maxResult); //设置每页显示的记录数
        getTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                String hql = new StringBuilder().append("from") //添加FROM字段
                        .append(GenericsUtils.getGenericName(entityClass)) //添加对象类型
                        .append(" ") //添加空格
                        .append(where == null ? "" : where) //如果where为null就添加空格，反之添加 where
                        .append(createOrderBy(orderby)) //添加排序条件参数
                        .toString(); //转化为字符串
                Query query = session.createQuery(hql); //执行查询
                setQueryParams(query, queryParams); //为查询参数赋值
                List<T> list = null; //定义 List 对象
                //如果 maxResult < 0, 则查询所有
                if (maxResult < 0 && pageNo < 0) {
                    list = query.list(); //将查询结果转化为 List 对象
                } else {
                    list = query.setFirstResult(getFirstResult(pageNo, maxResult)) //设置分页起始位置
                            .setMaxResults(maxResult) //设置每页显示的记录数
                            .list(); //将查询结果转化为 List 对象
                    //定义查询总记录数的 hql 语句
                    hql = new StringBuilder().append("select count(*) from ") //添加hql语句
                            .append(GenericsUtils.getGenericName(entityClass)) //添加对象类型
                            .append(" ") //添加空格
                            .append(where == null ? "" : where) //如果where为null就添加空格,反之添加where
                            .toString(); //转化为字符串
                    query = session.createQuery(hql); //执行查询
                    setQueryParams(query, queryParams); //设置hql参数
                    int totalRecords = ((Long) query.uniqueResult()).intValue(); //类型转换
                    pageModel.setTotalRecords(totalRecords); //设置总记录数
                }
                pageModel.setList(list); //将查询的list对象放入实体对象中
                return null;
            }
        });
        return pageModel;
    }

    /**
     * 获取分页查询中结果集的起始位置
     * @param pageNo 第几页
     * @param maxResult 页面显示的记录数
     * @return 起始位置
     */
    private int getFirstResult(int pageNo, int maxResult) {
        int firstResult = (pageNo - 1) * maxResult;
        return firstResult < 0 ? 0 : firstResult;
    }

    /**
     * 对 query 中的参数赋值
     * @param query
     * @param queryParams
     */
    protected void setQueryParams(Query query, Object[] queryParams) {
        if (queryParams != null && queryParams.length > 0) {
            for(int i = 0; i < queryParams.length; i++) {
                query.setParameter(i, queryParams[i]);
            }
        }
    }

    /**
     * 创建排序 hql 语句
     * @param orderby
     * @return String 排序字符串
     */
    private String createOrderBy(Map<String, String> orderby) {
        StringBuffer sb = new StringBuffer("");
        if (orderby != null && orderby.size() > 0) {
            sb.append(" order by ");
            for (String key : orderby.keySet()) {
                sb.append(key).append(" ").append(orderby.get(key)).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 获取 Session 对象
     * @return Session
     */
    protected Session getSession() {
        return (!this.template.isAllowCreate() ?
                SessionFactoryUtils.getSession(this.template.getSessionFactory(), false) :
                SessionFactoryUtils.getSession(this.template.getSessionFactory(),
                        this.template.getEntityInterceptor(),
                        this.template.getJdbcExceptionTranslator()));
    }
}
