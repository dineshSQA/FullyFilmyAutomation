package com.fullyfilmy.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelUtils {
	public static Iterator<Object[]> getTestData(String filePath, String sheetName) {
		List<Object[]> data = new ArrayList<>();

		try (FileInputStream fileInputStream = new FileInputStream(filePath);
				Workbook workbook = new XSSFWorkbook(fileInputStream)) {

			Sheet sheet = workbook.getSheet(sheetName);
			Iterator<Row> rowIterator = sheet.iterator();

			// Assuming the first row contains headers
			Row headerRow = rowIterator.next();
			int colCount = headerRow.getPhysicalNumberOfCells();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Object[] rowData = new Object[colCount];

				for (int i = 0; i < colCount; i++) {
					Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case STRING:
						rowData[i] = cell.getStringCellValue();
						break;
					case NUMERIC:
						rowData[i] = String.valueOf((int) cell.getNumericCellValue());
						break;
						// Add more cases as needed for different cell types

					}
				}

				data.add(rowData);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return data.iterator();
	}

	@DataProvider(name = "registrationData")
	public Iterator<Object[]> getRegistrationData() {
		return ExcelUtils.getTestData("./TestData/Regisration-Data.xlsx", "RegistrationData");
	}

	@DataProvider(name = "LoginData")
	public Iterator<Object[]> getSignInData() {
		return ExcelUtils.getTestData("./TestData/Login-Data.xlsx", "LoginData");
	}


	// ------     --------    ------ -------     -------- ------- -------- -------

	public static void writeToExcel(String filePath, String sheetName, List<String> searchResults) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(sheetName);

		int rowNum = 0;
		for (String result : searchResults) {
			System.out.println(result);
			Row row = sheet.createRow(rowNum++);
			Cell cell = row.createCell(0);
			cell.setCellValue(result);
		}

		try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
			workbook.write(fileOut);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@DataProvider(name = "searchData")
	public Object[][] searchData() {
		return new Object[][]{
			{"shirt"},
			{"notebook"},
			// Add more test data as needed
		};
	}

}
