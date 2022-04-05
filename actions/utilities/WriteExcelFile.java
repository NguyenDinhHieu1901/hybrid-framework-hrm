package utilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;


public class WriteExcelFile {
	private static final int COLUMN_INDEX_ID = 0;
	private static final int COLUMN_INDEX_TITLE = 1;
	private static final int COLUMN_INDEX_PRICE = 2;
	private static final int COLUMN_INDEX_QUANTITY = 3;
	private static final int COLUMN_INDEX_TOTAL = 4;
	private static CellStyle cellStyleFormatNumber = null;
	
	public static void main(String[] args) {
		
	}
	
	public static void writeExcel(List<Book> book, String excelFilePath) {
		// Create workbook
		
	}
	
	// Create dummy data
	private static List<Book> getBooks(){
		List<Book> listBook = new ArrayList<>();
		Book book;
		for (int i = 1; i <= 5; i++) {
			book = new Book(i, "Book " + i, i * 2, i * 1000.1);
			listBook.add(book);
		}
		return listBook;
	}
	
	// Create workbook
}
