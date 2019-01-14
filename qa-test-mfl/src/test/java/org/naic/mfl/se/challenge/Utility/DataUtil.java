package org.naic.mfl.se.challenge.Utility;


import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Row;



import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class DataUtil {

    public static ArrayList<Object[]> readFromExcel(String excelFilePath) throws  Exception{

        ArrayList<Object[]> userlist=new ArrayList<Object[]>();
       FileInputStream inputStream = new FileInputStream(new File(excelFilePath));


        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet firstSheet = workbook.getSheetAt(0);
      //  HSSFRow row=firstSheet.getRow(1);
        int rowcnt=firstSheet.getLastRowNum()+1;
       // Iterator<Row> iterator = firstSheet.iterator();

       // while (iterator.hasNext()) {
        for(int i=2;i<=rowcnt;i++){

            HSSFRow nextRow=firstSheet.getRow(1);
            String Id_gender=nextRow.getCell(0).getStringCellValue();
            String Firstname=nextRow.getCell(1).getStringCellValue();
            String Lastname=nextRow.getCell(2).getStringCellValue();
            String Email=nextRow.getCell(3).getStringCellValue();
            String Password=nextRow.getCell(4).getStringCellValue();
            String Days=nextRow.getCell(5).getStringCellValue();
            String Months=nextRow.getCell(6).getStringCellValue();
            String Years=nextRow.getCell(7).getStringCellValue();
            String Company=nextRow.getCell(8).getStringCellValue();
            String Address1=nextRow.getCell(9).getStringCellValue();
            String Address2=nextRow.getCell(10).getStringCellValue();
            String City=nextRow.getCell(11).getStringCellValue();
            String State=nextRow.getCell(12).getStringCellValue();
            String Postcode=nextRow.getCell(13).getStringCellValue();
            String Other=nextRow.getCell(14).getStringCellValue();
            String Phone=nextRow.getCell(15).getStringCellValue();
            String Phone_mobile=nextRow.getCell(16).getStringCellValue();
            String Alias=nextRow.getCell(17).getStringCellValue();

            Object[] tmp={Id_gender,Firstname,Lastname,Email,Password,Days,Months,Years,Company,
            Address1,Address2, City, State, Postcode, Other, Phone, Phone_mobile, Alias};
            userlist.add(tmp);
        }

        workbook.close();
        inputStream.close();
        return userlist;

    }


}
