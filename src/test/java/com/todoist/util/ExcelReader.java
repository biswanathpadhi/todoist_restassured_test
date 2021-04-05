package com.todoist.util;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

// Could be used to read values from Excel
public class ExcelReader {

    public static Workbook book;
    public static Sheet sheet;
    private static Logger logger;

    public ExcelReader() {
        logger = LogManager.getLogger();
    }


    public static synchronized Object[][] getTestData(String sheetName) throws IOException, InvalidFormatException {
        String testdataFileName = "TestData.xlsx";

        try(InputStream in = ExcelReader.class.getClassLoader().getResourceAsStream("data/" + testdataFileName)) {
            book = WorkbookFactory.create(in);
            sheet = book.getSheet(sheetName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        int rowCount = sheet.getLastRowNum();
        int colCount = sheet.getRow(0).getLastCellNum();
        Object[][] data = null;

        try {
            data = new Object[rowCount][colCount];
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }

        return data;
    }

    public String getDateTime() {

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy H-mm-ss");
        Date date = new Date();
        return dateFormat.format(date);

    }

}
