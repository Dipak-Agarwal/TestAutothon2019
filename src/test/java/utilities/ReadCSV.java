package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadCSV {
	
	static String line = "";
	static String csvSplitBy = ",";
	
	public static HashMap<String, String> getMap(String[] headers, String[] data)
	{
		HashMap<String, String> fieldMap = new HashMap<String, String>();
		for(int i=0; i<headers.length; i++)
		{
			fieldMap.put(headers[i], data[i]);
		}
		return fieldMap;
	}
	
	public static ArrayList<HashMap<String, String>> readFile(String csvFile) throws IOException
	{
		
		ReadProperties readPropObj = new ReadProperties();
		String csvFileLocation = readPropObj.getPropertyValue(csvFile);
		
		ArrayList<HashMap<String,String>> fieldMapList = new ArrayList<HashMap<String,String>>();
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(csvFileLocation));
		boolean isHeader = true;
		String[] headers = null;
		int index = 0;
		while(br.ready())
		{
			String data[] = br.readLine().split(csvSplitBy);
			if(isHeader)
			{
				headers = data;
				isHeader = false;
			}
			else
			{
				fieldMapList.add(index++, getMap(headers, data));
			}
		}
		return fieldMapList;
	}

}
