package rahulshettyacademy.datadrivenfromsheet;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class Excelprovider {
	@Test
	public void getExcel() throws IOException {
		
		FileInputStream fis= new FileInputStream("H://demodatarahul.xlsx");
		XSSFWorkbook wb= new XSSFWorkbook(fis);
		XSSFSheet sheet= wb.getSheetAt(1);
		int rowcount=sheet.getPhysicalNumberOfRows();
		XSSFRow row= sheet.getRow(0);
		int colcount=row.getLastCellNum();
		Object Data [][]=new Object[rowcount-1][rowcount];
		
		for(int i=0;i<rowcount-1; i++)
		{
			row= sheet.getRow(i+1);
			for(int j=0;j<colcount;j++)
			{
			System.out.println(row.getCell(j));
			}
		}
		
	}

}
