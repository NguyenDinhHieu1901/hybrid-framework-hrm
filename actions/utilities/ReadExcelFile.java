package utilities;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {
	XSSFWorkbook workBook;
	XSSFSheet sheet;

	public ReadExcelFile(String excelFilePath) {
		try {
			File s = new File(excelFilePath);
			FileInputStream stream = new FileInputStream(s);
			workBook = new XSSFWorkbook(stream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getData(int sheetNumber, int row, int column) {
		sheet = workBook.getSheetAt(sheetNumber);
		String data = sheet.getRow(row).getCell(column).getStringCellValue();
		return data;
	}

	public int getRowCount(int sheetIndex) {
		int row = workBook.getSheetAt(sheetIndex).getLastRowNum();
		row = row + 1;
		return row;
	}
}
