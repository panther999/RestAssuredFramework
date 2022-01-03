package com.dataprovider.define;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataproviderUtility {
	
	
	
	/**
	 * 
	 * @param nameOfExcelFile 
	 * <br> Pass this as nameOfExcelFile.sheetName
	 * <br> Example SearchData.sheet1
	 * @return List
	 * Use this method to get individual row data at a time
	 * 
	 */
	public Iterator<Object[]> readFromExcelIterator(String nameOfExcelFile){
		List<HashMap<String, String>> fullDataMap = new ArrayList<HashMap<String,String>>();
		String filename = "";
		String sheetname = "";
		try {
			if (! nameOfExcelFile.equalsIgnoreCase("")) {
				
				String[] arrFilenameSheet = nameOfExcelFile.split("\\.");
				filename = arrFilenameSheet[0] + ".xlsx";
				
				if(arrFilenameSheet.length == 2) {
					sheetname = arrFilenameSheet[1];
				}
				else {
					sheetname = "Sheet1";
				}
				
				//read file from resource
				
				InputStream file = getClass().getResourceAsStream("/" + filename);
		        XSSFWorkbook workbook = new XSSFWorkbook(file);
		        XSSFSheet sheet = workbook.getSheet(sheetname);
		        Iterator<Row> itRow = sheet.rowIterator();
		        Row firstRow = itRow.next();
		        while(itRow.hasNext()) {
		        	Row r = itRow.next();
		        	HashMap<String,String> dataMap = new HashMap<String,String>();
		        	for(int colCount=r.getFirstCellNum(); colCount <= r.getLastCellNum()-1; colCount++) {
		        		Cell cell = r.getCell(colCount);
		        		
		        		dataMap.put(firstRow.getCell(colCount).getStringCellValue(), cell.getStringCellValue());
		        	}
		        	fullDataMap.add(dataMap);
		        }
		        file.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		
		int sizeOfData = fullDataMap.size();
		List<Object[]> listObject = new ArrayList<Object[]>();

		for(int i = 0; i<= sizeOfData -1; i++) {
		   	Object[] arrObj = new Object[1];
		   	arrObj[0] = fullDataMap.get(i);
		   	listObject.add(arrObj);
		}
			
		return listObject.iterator();
	}
	
	
	
	
	public Iterator<Object[]> readFromExcelIteratorFull(String nameOfExcelFile){
		List<HashMap<String, String>> fullDataMap = new ArrayList<HashMap<String,String>>();
		String filename = "";
		String sheetname = "";
		try {
			if (! nameOfExcelFile.equalsIgnoreCase("")) {
				
				String[] arrFilenameSheet = nameOfExcelFile.split("\\.");
				filename = arrFilenameSheet[0] + ".xlsx";
				
				if(arrFilenameSheet.length == 2) {
					sheetname = arrFilenameSheet[1];
				}
				else {
					sheetname = "Sheet1";
				}
				
				//read file from resource
				
				InputStream file = getClass().getResourceAsStream("/" + filename);
		        XSSFWorkbook workbook = new XSSFWorkbook(file);
		        XSSFSheet sheet = workbook.getSheet(sheetname);
		        Iterator<Row> itRow = sheet.rowIterator();
		        Row firstRow = itRow.next();
		        while(itRow.hasNext()) {
		        	Row r = itRow.next();
		        	HashMap<String,String> dataMap = new HashMap<String,String>();
		        	for(int colCount=r.getFirstCellNum(); colCount <= r.getLastCellNum()-1; colCount++) {
		        		Cell cell = r.getCell(colCount);
		        		
		        		dataMap.put(firstRow.getCell(colCount).getStringCellValue(), cell.getStringCellValue());
		        	}
		        	fullDataMap.add(dataMap);
		        }
		        file.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		
		int sizeOfData = fullDataMap.size();
		List<Object[]> listObject = new ArrayList<Object[]>();
	   	Object[] arrObj = new Object[sizeOfData];

		for(int i = 0; i<= sizeOfData -1; i++) {
		   	arrObj[i] = fullDataMap.get(i);
		}
	   	listObject.add(arrObj);
	
		return listObject.iterator();
	}
	

}
