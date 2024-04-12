package test.apachepoi;

//Workbook-->Sheet-->Rows->Cells

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class WriteXLSdata {

    private ArrayList<DataToWrite> data;

    /*

    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Test Summary");
    String testSummary[][] = { {"EmpID", "Name", "Job"},
            {"101", "David", "Engineer"},
            {"102", "Smith", "Manager"},
            {"103", "Scott", "Analyst"}
    };
    //Using for loop
    int rows = testSummary.length;
    int cols = testSummary[0].length;

    for (int r = 0; r < rows; r++) {
        XSSFRow row = sheet.createRow(r);
        for (int c = 0; c < cols; c++) {
            XSSFCell cell = row.createCell(c);
            cell.setCellValue(testSummary[r][c]);
        }
    }
    String filePath=".\\src\\test\\resources\\test_sumary.xlsx";
    FileOutputStream outputStream = new FileOutputStream(filePath);
    workbook.write(outputStream);
    outputStream.close(); */
    public void writtingInExcel(ArrayList<DataToWrite> totalData) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Test Summary");
        int rows = totalData.size();
        int cols = 4;

        for (int r = 0; r < rows; r++) {
            XSSFRow row = sheet.createRow(r);
            for (int c = 0; c < cols; c++) {
                XSSFCell cell = row.createCell(c);
                switch (c) {
                    case 0:
                        cell.setCellValue(totalData.get(r).getTestCaseId());
                        break;

                    case 1:
                        cell.setCellValue(totalData.get(r).getPassed());
                        break;
                    case 2:
                        cell.setCellValue(totalData.get(r).getFailed());
                        break;
                    case 3:
                        cell.setCellValue(totalData.get(r).getPassed());
                }

            }

        }
    }
}




