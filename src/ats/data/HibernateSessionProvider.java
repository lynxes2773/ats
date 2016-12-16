package ats.data;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 
public class HibernateSessionProvider
{
     
    private static SessionFactory sessionFactory;
    static final Logger logger = LogManager.getLogger(HibernateSessionProvider.class.getName());
    
    static
    {
        try 
        {
            sessionFactory = new Configuration().configure("http://localhost:8080/ats/resources/xml/ats.cfg.xml").buildSessionFactory();
        } 
        catch (Throwable e) 
        {
        	throw new ExceptionInInitializerError(e);
        }
    }
 
    public static void setSessionFactory(SessionFactory sessionFactory) {
		HibernateSessionProvider.sessionFactory = sessionFactory;
	}

	public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
     
    public static void shutDown(){
        //closes caches and connections
        getSessionFactory().close();
    }
}
