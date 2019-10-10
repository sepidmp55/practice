package com.selenium.libraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jxl.Sheet;
import jxl.Workbook;

public class ExcelManager {

	final static Logger logger = Logger.getLogger(ExcelManager.class);
	
	private static String file;
	private static XSSFSheet excelSheet;
	private static XSSFWorkbook excelWBook;
	private static XSSFCell cell;
	private static XSSFRow row;
	
	public ExcelManager(String excelFileName){
		File myFile = new File(excelFileName);
		file = myFile.getAbsolutePath();
	}
	
	public String[][] getExcelData(String sheetName) {
		String[][] arrayExcelData = null;
		try {
			FileInputStream fs = new FileInputStream(file);
			Workbook wb = Workbook.getWorkbook(fs);
			Sheet sh = wb.getSheet(sheetName);

			int totalCols = sh.getColumns();
			int totalRows = sh.getRows();

			arrayExcelData = new String[totalRows - 1][totalCols];
			for (int i = 1; i < totalRows; i++) {
				// reading rows
				for (int j = 0; j < totalCols; j++) {
					// reading columns
					arrayExcelData[i - 1][j] = sh.getCell(j, i).getContents();
				}
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
		}
		return arrayExcelData;
	}
	
	public static void setCellData(String inputData, int rowNum, int colNum) {
		FileOutputStream fileOut = null;
		try {
			row = excelSheet.createRow(rowNum);
			cell = row.getCell(colNum);
			if (cell == null) {
				cell = row.createCell(colNum);
				cell.setCellValue(inputData);
				fileOut = new FileOutputStream(file);
				excelWBook.write(fileOut);
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
		} finally {
			try {
				fileOut.flush();
				fileOut.close();
			} catch (Exception e) {
				logger.error("Error: ", e);
			}
		}
	}
	
	public static String getCellData(int rowNum, int colNum) {
		String cellData = null;
		try {
			cell = excelSheet.getRow(rowNum).getCell(colNum);
			cellData = cell.getStringCellValue();
		} catch (Exception e) {
			logger.error("Error: ", e);
		}
		return cellData;
	}

	public static void setExcelFile(String sheetName) {
		try {
			//FileOutputStream excelfile = new FileOutputStream(file);			
			excelWBook = new XSSFWorkbook();
			excelSheet = excelWBook.createSheet(sheetName);
		} catch (Exception e) {
			logger.error("Error: ", e);
		}
	}
	
	
/*	// Create or write excel file
	public static void main(String[] args) {
		ExcelManager excelWrite = new ExcelManager("C:/aaa/myTestData.xlsx");
		setExcelFile("mySheet1");
		excelWrite.setCellData("I love Java programming!", 1, 1);
		logger.info("excel file is created.");
		logger.info("File location: " + "C:/aaa/myTestData.xlsx");		
	}
*/
	
	public static void main(String[] args) {
		ExcelManager excelRead = new ExcelManager("src/test/resources/TestData/CalculaterTestData2.xls");
		//setExcelFile("Sheet1");
		String[][] cellData = excelRead.getExcelData("Sheet1");
		logger.info("data: " + cellData);
	}
	
}



















