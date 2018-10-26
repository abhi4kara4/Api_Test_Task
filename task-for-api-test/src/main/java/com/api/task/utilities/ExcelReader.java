package com.api.task.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * This class loads the excelsheet in the constructor and provides various methods to read the 
 * elements from rows and columns
 * @author  Abhijit Karan
 *
 */
public class ExcelReader {

	private static final Logger LOGGER = Logger.getLogger(ExcelReader.class);

	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;

	
	
	
	/**
	 * This method will provide the test data to our test class.
	 * @param FilePath
	 * @param SheetName
	 * @param totalCols
	 * @return
	 * @throws Exception
	 */
	public static Object[][] getTableArray(String FilePath, String SheetName,int totalCols) throws Exception {   
		LOGGER.debug("Starting method : getCellData");
		
		// Variable declaration Start
		String[][] tabArray = null;
		int startRow = 1;
		int startCol = 1;
		int ci,cj;
		
		// Variable declaration End
		
		try {
			FileInputStream ExcelFile = new FileInputStream(FilePath);

			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			int totalRows = ExcelWSheet.getLastRowNum();
			// Iterate through each Row
			tabArray=new String[totalRows][totalCols];
			ci=0;
			for (int i=startRow;i<=totalRows;i++, ci++) {           	   

				cj=0;
				// Iterate through each Column
				for (int j=startCol;j<=totalCols;j++, cj++){

					tabArray[ci][cj]=getCellData(i,j);

					LOGGER.debug(tabArray[ci][cj]);  
				}
			}
		}
		catch (FileNotFoundException e){
			LOGGER.error("Could not read the Excel sheet due to : "+e.getMessage());
		}
		catch (IOException e){
			LOGGER.error("Could not read the Excel sheet due to : "+e.getMessage());
		}

		LOGGER.debug("Ending method : getCellData");
		return(tabArray);
	}

	/**
	 * This function will return the cell data for a specific row and column number
	 * @param RowNum
	 * @param ColNum
	 * @return cellData
	 * @throws Exception
	 */
	public static String getCellData(int RowNum, int ColNum) throws Exception {
		LOGGER.debug("Starting method : getCellData");
		String CellData = "";
		DataFormatter formatter = new DataFormatter();
		Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		CellData = formatter.formatCellValue(Cell);
		LOGGER.info("Cell Data received for Row Number :"+RowNum+" & Column Number :"+ColNum+" is :--> "+CellData);
		LOGGER.debug("Ending method : getCellData");
		return CellData;

	}
}
