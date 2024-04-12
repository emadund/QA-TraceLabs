package test.apachepoi;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class ReadXLSdata {

    private int id;
    private String username;
    private String email;
    private String password;
    private String type;

    public ReadXLSdata() {
    }

    public ReadXLSdata(int id, String username, String email, String password, String type) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static String[][] getData(String excelSheetName) throws Exception {
        File f = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\tracelabs_apachepoi.xlsx");
        FileInputStream fis = new FileInputStream(f);
        Workbook wb= WorkbookFactory.create(fis);
        Sheet sheetName = wb.getSheet(excelSheetName);

        int totalRows = sheetName.getLastRowNum();
        System.out.println("Total rows: "+totalRows);
        Row rowCells = sheetName.getRow(0);
        int totalCols = rowCells.getLastCellNum();
        System.out.println("Total columns: "+totalCols);

        DataFormatter format = new DataFormatter();
        String testData[][]=new String[totalRows][totalCols];
        for(int i=1; i<=totalRows;i++) {
            for (int j=0;j<totalCols;j++) {
                testData[i-1][j]=format.formatCellValue(sheetName.getRow(i).getCell(j));
                System.out.println(testData[i-1][j]);
            }
        }
        return testData;
    }

    public static ArrayList<ReadXLSdata> getList(String excelSheetName) throws Exception {
        ArrayList<ReadXLSdata> data = new ArrayList<>();
        String[][] totalData = getData(excelSheetName);
        for (int i=0; i<totalData.length;i++) {
            ReadXLSdata singleData = new ReadXLSdata();
            singleData.setId(Integer.parseInt(totalData[i][0]));
            singleData.setUsername(totalData[i][1]);
            singleData.setEmail(totalData[i][2]);
            singleData.setPassword(totalData[i][3]);
            singleData.setType(totalData[i][4]);
            data.add(singleData);
        }
        return data;
    }
}
