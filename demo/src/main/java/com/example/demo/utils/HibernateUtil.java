package com.example.demo.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author jixia
 * @Description TODO
 * @date 20/11/2023-17:09
 */

public class HibernateUtil {

    private static final Configuration config;

    private static final SessionFactory factory;

    static {
        config=new Configuration().configure();
        factory=config.buildSessionFactory();
    }

    public static Session getSession() {
        return factory.openSession();
    }


}


