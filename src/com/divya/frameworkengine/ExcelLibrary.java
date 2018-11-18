package com.divya.frameworkengine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelLibrary {

	/**
	 * @param args
	 */	
	public String getExcelData(String sheetName, int rowNum, int colNum){
		String retVal=null;
		try {
			String filePath="C:\\Users\\divya\\Documents\\DivyaSeleniumFramework\\DivyaSeleniumFramework\\tests\\testscenarios.xlsx";
			FileInputStream fis = new FileInputStream(filePath);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet s = wb.getSheet(sheetName);
			Row r = s.getRow(rowNum);
			Cell c = r.getCell(colNum);
			retVal=c.getStringCellValue();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retVal;
	}
	public int getRowCount(String sheetName){
		int retVal=0;
		try {
			String filePath="C:\\Users\\divya\\Documents\\DivyaSeleniumFramework\\DivyaSeleniumFramework\\tests\\testscenarios.xlsx";
			FileInputStream fis = new FileInputStream(filePath);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet s = wb.getSheet(sheetName);
			retVal=s.getLastRowNum();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retVal;
	}
	public String setExcelData(String sheetName, int rowNum, int colNum, String data){
		String retVal=null;
		try {
			String filePath="C:\\Users\\divya\\Documents\\DivyaSeleniumFramework\\DivyaSeleniumFramework\\tests\\testscenarios.xlsx";
			FileInputStream fis = new FileInputStream(filePath);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet s = wb.getSheet(sheetName);
			Row r = s.getRow(rowNum);
			Cell c = r.createCell(colNum);
			c.setCellType(Cell.CELL_TYPE_STRING);
			c.setCellValue(data);
			FileOutputStream fos=new FileOutputStream(filePath);
			wb.write(fos);
			fos.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retVal;
	}

}






