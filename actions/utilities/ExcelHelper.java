package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import commons.GlobalConstants;

public class ExcelHelper {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		FileInputStream inputFile = null;
		try {
			inputFile = new FileInputStream(new File(GlobalConstants.getGlobalConstants().getProjectPath() + File.separator + "student.xlsx"));
			// Creating Workbook instance that refers to .xlsx file
			Workbook workbook = new XSSFWorkbook(inputFile);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					case STRING:
						System.out.println(cell.getStringCellValue() + "\t\t\t");
						break;
					case NUMERIC:
						System.out.println(cell.getNumericCellValue() + "\t\t\t");
						break;
					default:
						System.out.println("");
						break;

					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (inputFile != null) {
				inputFile.close();
			}
		}

	}
}
