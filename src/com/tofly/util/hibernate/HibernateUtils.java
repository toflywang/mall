package com.tofly.util.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.awt.*;

/**
 * Hibernate 工具类，用于获取 Session
 * Created by lenovo on 2017/3/30.
 */
public class HibernateUtils {
    private static SessionFactory factory = null;
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
    private static Configuration cfg = new Configuration();

    static {
        try {
            cfg.configure();
            factory = cfg.buildSessionFactory();
        } catch (HeadlessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取 Session 对象
     * @return Session 对象
     */
    public static Session getSession() {
        Session session = threadLocal.get();
        if (session == null || !session.isOpen()) {
            if (factory == null) {
                rebuildSessionFactory();
            }
            session = (factory != null) ? factory.openSession() : null;
            threadLocal.set(session);
        }
        return session;
    }

    /**
     * 获取 SessionFactory 对象
     * @return SessionFactory 对象
     */
    public static SessionFactory getSessionFactory() {
        return factory;
    }

    public static void closeSession() {
        Session session = threadLocal.get();
        threadLocal.remove();
        if (session != null) {
            if (session.isOpen()) {
                session.close();
            }
        }
    }

    /**
     * 创建 SessionFactory 对象
     */
    private static void rebuildSessionFactory() {
        try {
            cfg.configure();
            factory = cfg.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
