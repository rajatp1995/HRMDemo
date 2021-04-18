package orangeHRM;

import java.io.File;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {
	public void writeExcel(String sheetName,String[] dataToWrite) throws IOException{

		String FilePath = "C:\\Users\\rajat\\eclipse\\TestResult.xlsx";
		FileInputStream inputStream = new FileInputStream(FilePath);
		Workbook wBook = null;
		
		String fileName = "TestResult.xlsx";
		
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if(fileExtensionName.equals(".xlsx")){
			wBook = new XSSFWorkbook(inputStream);
		}
		else if(fileExtensionName.equals(".xls")){
			wBook = new HSSFWorkbook(inputStream);
		}     

		Sheet sheet = wBook.getSheet(sheetName);

		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		Row row = sheet.getRow(0);

		Row newRow = sheet.createRow(rowCount+1);
		
		CellStyle stylePass = wBook.createCellStyle(); 
		stylePass.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());  
		stylePass.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        CellStyle styleFail = wBook.createCellStyle(); 
        styleFail.setFillForegroundColor(IndexedColors.RED.getIndex());  
        styleFail.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		for(int j = 0; j < row.getLastCellNum(); j++){
			Cell cell = newRow.createCell(j);
			cell.setCellValue(dataToWrite[j]);
			if (dataToWrite[j]=="Passed") cell.setCellStyle(stylePass);
			else if (dataToWrite[j]=="Failed") cell.setCellStyle(styleFail);
		}

		inputStream.close();

		FileOutputStream outputStream = new FileOutputStream(FilePath);

		wBook.write(outputStream);
		outputStream.close();
	}

}
