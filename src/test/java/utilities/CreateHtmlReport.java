package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class CreateHtmlReport {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String validationExcel = "./iflowDetails/ComponentValidation.xlsx";
		String sheetName = "Components";
		String htmlReport = "./Reports/Product_Profile_Validation_Report.html";
		String[] dataFromExcel=new String[1000];
		int i=1,j=0,k=0;
		HashMap<String,String> componentNamesWithValidationStatus = new HashMap<String,String>();
		HashMap<String,String> componentNamesWithValidationStatusAsFailed = new HashMap<String,String>();
		HashMap<String,String> componentNamesWithValidationStatusAsPassed = new HashMap<String,String>();
		HashMap<String,String> componentNamesWithValidationStatusAsRemaining = new HashMap<String,String>();
		try
		{
			File file = new File(validationExcel);
			FileInputStream fis = new FileInputStream(file);
			Workbook wrkBook = WorkbookFactory.create(fis);
			Sheet sheet = wrkBook.getSheet(sheetName);
			String componentName = null;
			String validationStatus = null;
			while(!(dataFromExcel[k]=sheet.getRow(i).getCell(j).getStringCellValue()).equals(null) && dataFromExcel[k].length()>0 && !(sheet.getRow(i).getCell(j).getStringCellValue()).equals(""))
			{
				componentName = dataFromExcel[k];
				validationStatus = sheet.getRow(i).getCell(j+1).getStringCellValue();
				componentNamesWithValidationStatus.put(componentName, validationStatus);
				i++;
				k++;
			}
		}
		catch (Exception e) 
		{
			System.out.println("Error : "+e.getMessage());
		}
		String component = null;
		String validatedOrNot = null;
		if(!componentNamesWithValidationStatus.isEmpty())
		{
			Iterator  componentNamesWithValidationStatusIterator= componentNamesWithValidationStatus.entrySet().iterator();
			while (componentNamesWithValidationStatusIterator.hasNext()) 
			{
				Map.Entry pair = (Map.Entry)componentNamesWithValidationStatusIterator.next();
				component = (String)pair.getKey();
				validatedOrNot = (String)pair.getValue();
				System.out.println(component+" : "+validatedOrNot);
				if(validatedOrNot.equals("Passed"))
				{
					componentNamesWithValidationStatusAsPassed.put(component, "Passed");
				}
				else if(validatedOrNot.equals("Failed"))
				{
					componentNamesWithValidationStatusAsFailed.put(component, "Failed");
				}
				else if(validatedOrNot.equals("No"))
				{
					componentNamesWithValidationStatusAsRemaining.put(component, "Not Validated");
				}
			}
		}
		int passCount = 1, failCount = 1, remainingCount = 1, numberOfPassed = 0, numberOfFailed = 0, numberOfRemaining = 0;
		try
		{
			//define a HTML String Builder
            StringBuilder htmlStringBuilder=new StringBuilder();
            //append html header and title
            //htmlStringBuilder.append("<html><head><style>table {font-family: arial, border:1 ;bordercolor: #000000;width: 100%;}th,td{border: 1px solid #dddddd;text-align: left;padding: 8px;}</style><title>Product Profile Validation Status</title></head>");
            htmlStringBuilder.append("<html><head><title>Product Profile Validation Status</title></head>");
            //append body
            htmlStringBuilder.append("<body>");
            //append table
            htmlStringBuilder.append("<h1><center>Component Validation Status Report</center></h1>");
            htmlStringBuilder.append("<table border=\"1\" bordercolor=\"#D2B4DE\" font-family=\"arial\" width=100%>");
            //htmlStringBuilder.append("<table>");
            //append row
            htmlStringBuilder.append("<tr bgcolor=\"#808B96\"><td><h3>Serial No.</h3></td><td><h3>Components Names</h3></td><td><h3>Validation Status</h3></td></tr>");
            if(!componentNamesWithValidationStatusAsPassed.isEmpty())
    		{
    			Iterator  componentNamesWithValidationStatusAsPassedIterator= componentNamesWithValidationStatusAsPassed.entrySet().iterator();
    			while (componentNamesWithValidationStatusAsPassedIterator.hasNext()) 
    			{
    				Map.Entry pair = (Map.Entry)componentNamesWithValidationStatusAsPassedIterator.next();
    				//append row
    				htmlStringBuilder.append("<tr><td>"+(passCount++)+"</td><td><b>"+(String)pair.getKey()+"</b></td><td bgcolor=\"#00ff00\">"+(String)pair.getValue()+"</td></tr>");
    				numberOfPassed++;
    			}
    		}
            
            //htmlStringBuilder.append("<table border=\"1\" bordercolor=\"#000000\">");
            //append row
            //htmlStringBuilder.append("<th ");
            //htmlStringBuilder.append("<tr><td><b>Serial No.</b></td><td><b>Components Validated</b></td></tr>");
            if(!componentNamesWithValidationStatusAsFailed.isEmpty())
    		{
    			Iterator  componentNamesWithValidationStatusAsFailedIterator= componentNamesWithValidationStatusAsFailed.entrySet().iterator();
    			while (componentNamesWithValidationStatusAsFailedIterator.hasNext()) 
    			{
    				Map.Entry pair = (Map.Entry)componentNamesWithValidationStatusAsFailedIterator.next();
    				//append row
    				htmlStringBuilder.append("<tr><td>"+(passCount++)+"</td><td><b>"+(String)pair.getKey()+"</b></td><td bgcolor=\"#ff4000\">"+(String)pair.getValue()+"</td></tr>");
    				numberOfFailed++;
    			}
    		}
            if(!componentNamesWithValidationStatusAsRemaining.isEmpty())
    		{
    			Iterator  componentNamesWithValidationStatusAsRemainingIterator= componentNamesWithValidationStatusAsRemaining.entrySet().iterator();
    			while (componentNamesWithValidationStatusAsRemainingIterator.hasNext()) 
    			{
    				Map.Entry pair = (Map.Entry)componentNamesWithValidationStatusAsRemainingIterator.next();
    				//append row
    				htmlStringBuilder.append("<tr><td>"+(passCount++)+"</td><td><b>"+(String)pair.getKey()+"</b></td><td bgcolor=\"#3498DB\">"+(String)pair.getValue()+"</td></tr>");
    				numberOfRemaining++;
    			}
    		}
            
            float passPercentage = (float)(numberOfPassed*100)/(numberOfPassed+numberOfFailed+numberOfRemaining);
            float failPercentage = (float)(numberOfFailed*100)/(numberOfPassed+numberOfFailed+numberOfRemaining);
            float remainingPercentage = (float)(numberOfRemaining*100)/(numberOfPassed+numberOfFailed+numberOfRemaining);
            
            //close html file
            htmlStringBuilder.append("</table>");
            htmlStringBuilder.append("<h1>Final Analysis</h1>");
            htmlStringBuilder.append("<table border=\"1\" bordercolor=\"#D2B4DE\" font-family=\"arial\" width=40%>");
            htmlStringBuilder.append("<tr><td><b>Total Number of Components</b></td><td bgcolor=\"#BFC9CA\">"+(numberOfPassed+numberOfFailed+numberOfRemaining)+"</td></tr>");
            htmlStringBuilder.append("<tr><td><b>Number of Components Successfully Validated</b></td><td bgcolor=\"#00ff00\">"+(numberOfPassed)+"</td></tr>");
            htmlStringBuilder.append("<tr><td><b>Successful Validation of Components Percentage</b></td><td bgcolor=\"#00ff00\">"+Math.round(passPercentage*100.0)/100.0+"</td></tr>");
            htmlStringBuilder.append("<tr><td><b>Number of Components Unsuccessfully Validated</b></td><td bgcolor=\"#ff4000\">"+(numberOfFailed)+"</td></tr>");
            htmlStringBuilder.append("<tr><td><b>Failed validation of Components Percentage</b></td><td bgcolor=\"#ff4000\">"+Math.round(failPercentage*100.0)/100.0+"</td></tr>");
            htmlStringBuilder.append("<tr><td><b>Number of Components Untouched</b></td><td bgcolor=\"#3498DB\">"+(numberOfRemaining)+"</td></tr>");
            htmlStringBuilder.append("<tr><td><b>Untouched Components Percentage</b></td><td bgcolor=\"#3498DB\">"+Math.round(remainingPercentage*100.0)/100.0+"</td></tr>");
            htmlStringBuilder.append("</table>");
            //htmlStringBuilder.append("<tr><td>Total Number of Components</td><td>"+(numberOfPassed+numberOfFailed)+"</td></tr>");
            htmlStringBuilder.append("</body></html>");
            //write html string content to a file
            File htmlFile = new File(htmlReport);
            OutputStream outputStream = new FileOutputStream(htmlFile.getAbsoluteFile());
            Writer writer=new OutputStreamWriter(outputStream);
            writer.write(htmlStringBuilder.toString());
            writer.close();
            
			
		}
		catch(Exception e)
		{
			System.out.println("Error : "+e.getMessage());
		}
	}

}
