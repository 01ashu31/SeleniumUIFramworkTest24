package rahulshettyacademy.datadrivenfromsheet;

import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.text.DateFormatter;

import org.apache.poi.ss.format.CellDateFormatter;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderashu {

	DataFormatter formatter = new DataFormatter();

	@Test(dataProvider = "driveTest")
	public void testcaseData(String Greeting, String Communication, int id) {
		System.out.println(Greeting + Communication + id);

	}

	@DataProvider(name = "driveTest")
	public Object[][] getData() throws IOException {

//		Object[][] data= {{"hello","text",1},{"hello2","text2",2},{"Solo", "call",3}};
		// every row of excel shuould be sent to 1 array

		FileInputStream fis = new FileInputStream("H://demodatarahul.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(1);
		int rowcount = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int colcount = row.getLastCellNum();
		Object data[][] = new Object[rowcount - 1][rowcount];

		for (int i = 0; i < rowcount - 1; i++) {
			row = sheet.getRow(i + 1);
			for (int j = 0; j < colcount; j++) {
				XSSFCell cell = row.getCell(j);

				data[i][j] = formatter.formatCellValue(cell);
			}
		}
		return data;

	}

}
