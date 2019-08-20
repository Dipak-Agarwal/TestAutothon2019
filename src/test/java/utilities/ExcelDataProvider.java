package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {

	XSSFWorkbook wb;
	File src;
	FileOutputStream fout;

	public ExcelDataProvider() {

		try {
			src = new File(System.getProperty("user.dir") + "/ScenarioSpecificTestData/OrangeHRM.xlsx");
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);

			System.out.println("Excel sheet is loaded and ready to use");
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
			System.out.println("Exception while loading Excel sheet");
		}

	}

	public String getData(String sheetname, int row, int column) {
		return wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
	}

	public int getNumericData(String sheetname, int row, int column) {
		return (int) wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
	}

	public double getNumericDataDouble(String sheetname, int row, int column) {
		return wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
	}

	public int getRowCount(String sheetname) {
		return wb.getSheet(sheetname).getPhysicalNumberOfRows();
	}

	public void writeData(String Sheetname, int row, int column, String value) {
		wb.getSheet(Sheetname).getRow(row).createCell(column).setCellValue(value);
	}

	public void startWriteExcel() {
		try {
			fout = new FileOutputStream(src);
		} catch (FileNotFoundException e) {

			System.out.println("Exception is " + e.getMessage());
		}
	}

	public void closeExcel() {
		try {

			wb.write(fout);
			wb.close();
		} catch (Exception e) {

			System.out.println("Unable to write this sheet");
		}

	}

}
