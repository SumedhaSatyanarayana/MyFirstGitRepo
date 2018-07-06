package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openxml4j.exceptions.InvalidFormatException;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase {
	
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 15;
	public static String Test_Data_Path = "J:\\workspace\\CRMTest\\src\\main\\java\\com\\crm\\qa\\testdata\\FreeCRMTestData.xlsx";
	static Workbook wb;
	static Sheet st;
	
	
	public void SwitchToFrame(){
		driver.switchTo().frame("mainpanel");
	}
	public static Object[][] getTestData(String sheetName) throws RuntimeException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException{
		FileInputStream file = null;
		try{
			file = new FileInputStream(Test_Data_Path); 
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
		wb=WorkbookFactory.create(file);
		st = wb.getSheet(sheetName);
		// 2d object array
		Object[][] data = new Object[st.getLastRowNum()][st.getRow(0).getLastCellNum()];
		System.out.println("Last Row Number"+ st.getLastRowNum());
		System.out.println("Last Cell Number" + st.getRow(0).getLastCellNum());
		for(int i = 0 ; i<st.getLastRowNum(); i++){
			for(int j = 0 ; j<st.getRow(0).getLastCellNum(); j++){
				data[i][j] = st.getRow(i+1).getCell(j).toString();
			}
		}
		return data;
	}
	
	public static void takeScreenshotAtEndOfTest() throws IOException{
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		//if(osName.startsWith("Win")){
		FileUtils.copyFile(sourceFile, new File(currentDir + "\\screenshots\\" + System.currentTimeMillis() + ".png"));
		//}
	}
	
	
}
