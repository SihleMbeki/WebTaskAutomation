package utilities;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;

public class ExcelReader {
	File excelFile = null;
	FileInputStream fi;
	Workbook workbook;
	Sheet sheet;

	public ExcelReader(String filename, int index) {
		try {
			excelFile = new File(filename);
			fi = new FileInputStream(excelFile);
			workbook = WorkbookFactory.create(fi);
			sheet = workbook.getSheetAt(index);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed to make file connection");
		}
	}

	public Sheet getSheet(int index) {
		return sheet;
	}

	public int rowCount() {
		int rows = 0;
		for (Row row : sheet) {
			rows += 1;
		}
		return rows;
	}

}
