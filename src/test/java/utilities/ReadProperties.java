package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadProperties {
	
	public String getPropertyValue(String key)
	{
		String value = null;
		try
		{	
			FileInputStream fis = new FileInputStream("./TestData/data.properties");
			FileInputStream fis1 = new FileInputStream("./TestData/ScenarioToCSVMapping.properties");
			Properties prop = new Properties();
			prop.load(fis);
			prop.load(fis1);
			value = prop.getProperty(key);
		}
		catch(Exception e)
		{
			System.out.println("Unable to find property value for "+key);
			System.out.println(e.getMessage());
		}
		return value;
	}

}
