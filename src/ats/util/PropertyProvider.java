package ats.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class PropertyProvider implements Provider {
	
	private Properties systemProperties=null;
	
	public PropertyProvider()
	{
		InputStream is = null;
		systemProperties = new Properties();
		try
		{
			is = this.getClass().getResourceAsStream("/resources/system/config.properties");
			 systemProperties.load(is);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public String getSystemProperty(String key)
	{
		return this.systemProperties.getProperty(key);
	}

}
