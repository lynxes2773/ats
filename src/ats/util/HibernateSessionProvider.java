package ats.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ats.biz.ApplicationManager;
 
public class HibernateUtil 
{
     
    private static SessionFactory sessionFactory;
    static final Logger logger = LogManager.getLogger(HibernateUtil.class.getName());
    
    static
    {
        try 
        {
            sessionFactory = new Configuration().configure("../resources/ats.cfg.xml").buildSessionFactory();
        } 
        catch (Throwable e) 
        {
        	throw new ExceptionInInitializerError(e);
        }
    }
 
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
     
    public static void shutDown(){
        //closes caches and connections
        getSessionFactory().close();
    }
}
