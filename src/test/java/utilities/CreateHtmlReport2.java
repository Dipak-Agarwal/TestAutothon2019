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

public class CreateHtmlReport2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String htmlReport = "./Reports/TestReport.html";
		String[] dataFromExcel=new String[1000];
		int i=1,j=0,k=0;
		HashMap<String,String> username = new HashMap<String,String>();
		HashMap<String,String> password = new HashMap<String,String>();
		HashMap<String,String> status = new HashMap<String,String>();
		try
		{
			//define a HTML String Builder
            StringBuilder htmlStringBuilder=new StringBuilder();
            //append html header and title
            //htmlStringBuilder.append("<html><head><style>table {font-family: arial, border:1 ;bordercolor: #000000;width: 100%;}th,td{border: 1px solid #dddddd;text-align: left;padding: 8px;}</style><title>Product Profile Validation Status</title></head>");
            htmlStringBuilder.append("<html><head><title>Test Report</title></head>");
            //append body
            htmlStringBuilder.append("<body>");
            //append table
            htmlStringBuilder.append("<h1><center>Status Report</center></h1>");
            htmlStringBuilder.append("<table border=\"1\" bordercolor=\"#D2B4DE\" font-family=\"arial\" width=100%>");
            //htmlStringBuilder.append("<table>");
            //append row
            htmlStringBuilder.append("<tr bgcolor=\"#808B96\"><td><h3>Serial No.</h3></td><td><h3>Components Names</h3></td><td><h3>Validation Status</h3></td></tr>");
    		htmlStringBuilder.append("<tr><td>admin</td><td><b>admin</b></td><td bgcolor=\"#00ff00\">Invalid</td></tr>");
    		htmlStringBuilder.append("<tr><td>admin1</td><td><b>admin1</b></td><td bgcolor=\"#00ff00\">Invalid</td></tr>");
    		htmlStringBuilder.append("<tr><td>admin2</td><td><b>admin2</b></td><td bgcolor=\"#00ff00\">Invalid</td></tr>");
            
            //close html file
            htmlStringBuilder.append("</table>");
//            htmlStringBuilder.append("<h1>Final Analysis</h1>");
//            htmlStringBuilder.append("<table border=\"1\" bordercolor=\"#D2B4DE\" font-family=\"arial\" width=40%>");
//            htmlStringBuilder.append("<tr><td><b>Total Number of Components</b></td><td bgcolor=\"#BFC9CA\">"+(numberOfPassed+numberOfFailed+numberOfRemaining)+"</td></tr>");
//            htmlStringBuilder.append("<tr><td><b>Number of Components Successfully Validated</b></td><td bgcolor=\"#00ff00\">"+(numberOfPassed)+"</td></tr>");
//            htmlStringBuilder.append("<tr><td><b>Successful Validation of Components Percentage</b></td><td bgcolor=\"#00ff00\">"+Math.round(passPercentage*100.0)/100.0+"</td></tr>");
//            htmlStringBuilder.append("<tr><td><b>Number of Components Unsuccessfully Validated</b></td><td bgcolor=\"#ff4000\">"+(numberOfFailed)+"</td></tr>");
//            htmlStringBuilder.append("<tr><td><b>Failed validation of Components Percentage</b></td><td bgcolor=\"#ff4000\">"+Math.round(failPercentage*100.0)/100.0+"</td></tr>");
//            htmlStringBuilder.append("<tr><td><b>Number of Components Untouched</b></td><td bgcolor=\"#3498DB\">"+(numberOfRemaining)+"</td></tr>");
//            htmlStringBuilder.append("<tr><td><b>Untouched Components Percentage</b></td><td bgcolor=\"#3498DB\">"+Math.round(remainingPercentage*100.0)/100.0+"</td></tr>");
//            htmlStringBuilder.append("</table>");
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
