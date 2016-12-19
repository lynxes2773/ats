package ats.data;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 
public class HibernateSessionProvider
{
	private static String PROD_CONFIG_LOCATION = "http://localhost:8080/ats/resources/xml/ats.cfg.xml";
	private static String TEST_CONFIG_LOCATION = "../resources/xml/ats.cfg.xml";

	private static SessionFactory sessionFactory;

    static final Logger logger = LogManager.getLogger(HibernateSessionProvider.class.getName());
    
    static
    {
        try 
        {
        	sessionFactory = new Configuration().configure(PROD_CONFIG_LOCATION).buildSessionFactory();
        } 
        catch (Exception e) 
        {
        	e.printStackTrace();
        }
    }
     
    public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutDown(){
        //closes caches and connections
        getSessionFactory().close();
    }
}
