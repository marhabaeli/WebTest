package org.naic.mfl.se.challenge.Utility;


import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import javax.jws.soap.SOAPBinding;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadWriteExcel {

   public static ArrayList<Object[]> readFromExcel(String excelFilePath) throws  Exception{

        ArrayList<Object[]> userlist=new ArrayList<Object[]>();
       FileInputStream inputStream = new FileInputStream(new File(excelFilePath));


        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet firstSheet = workbook.getSheetAt(0);
        int rowcnt=firstSheet.getLastRowNum();

        for(int i=1;i<=rowcnt;i++){

            XSSFRow nextRow=firstSheet.getRow(i);
//            String Id_gender=nextRow.getCell(0).getStringCellValue();
            String Firstname=nextRow.getCell(1).getStringCellValue();
            String Lastname=nextRow.getCell(2).getStringCellValue();
            String Email=nextRow.getCell(3).getStringCellValue();
            String Password=nextRow.getCell(4).getStringCellValue();
//            String Days=nextRow.getCell(5).getStringCellValue();
//            String Months=nextRow.getCell(6).getStringCellValue();
//            String Years=nextRow.getCell(7).getStringCellValue();
//            String Company=nextRow.getCell(8).getStringCellValue();
//            String Address1=nextRow.getCell(9).getStringCellValue();
//            String Address2=nextRow.getCell(10).getStringCellValue();
//            String City=nextRow.getCell(11).getStringCellValue();
//            String State=nextRow.getCell(12).getStringCellValue();
//            String Postcode=nextRow.getCell(13).getStringCellValue();
//            String Other=nextRow.getCell(14).getStringCellValue();
//            String Phone=nextRow.getCell(15).getStringCellValue();
//            String Phone_mobile=nextRow.getCell(16).getStringCellValue();
//            String Alias=nextRow.getCell(17).getStringCellValue();

//            Object[] tmp={Id_gender,Firstname,Lastname,Email,Password,Days,Months,Years,Company,
//            Address1,Address2, City, State, Postcode, Other, Phone, Phone_mobile, Alias};
            Object[] tmp={Email, Password, Firstname+" "+Lastname};
            userlist.add(tmp);
        }

        workbook.close();
        inputStream.close();
        return userlist;
    }

 /*  public  static ArrayList<UserInfo> readFromExcelFile(String excelFilePath) throws Exception {
       FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

       ArrayList<UserInfo> userlist=new ArrayList<UserInfo>() ;


       XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
       XSSFSheet firstSheet = workbook.getSheetAt(0);
       int rowcnt=firstSheet.getLastRowNum()+1;

       for(int i=2;i<=rowcnt;i++){

           UserInfo user=new UserInfo();

           XSSFRow nextRow=firstSheet.getRow(i);
           user.setId_gender(nextRow.getCell(0).getStringCellValue());
           user.setFirstname(nextRow.getCell(1).getStringCellValue());
           user.setLastname(nextRow.getCell(2).getStringCellValue());
           user.setEmail(nextRow.getCell(3).getStringCellValue());
           user.setPassword(nextRow.getCell(4).getStringCellValue());
           user.setDays(nextRow.getCell(5).getStringCellValue());
           user.setMonths(nextRow.getCell(6).getStringCellValue());
           user.setYears(nextRow.getCell(7).getStringCellValue());
           user.setCompany(nextRow.getCell(8).getStringCellValue());
           user.setAddress1(nextRow.getCell(9).getStringCellValue());
           user.setAddress2(nextRow.getCell(10).getStringCellValue());
           user.setCity(nextRow.getCell(11).getStringCellValue());
           user.setState(nextRow.getCell(12).getStringCellValue());
           user.setPostcode(nextRow.getCell(13).getStringCellValue());
           user.setOther(nextRow.getCell(14).getStringCellValue());
           user.setPhone(nextRow.getCell(15).getStringCellValue());
           user.setPhone_mobile(nextRow.getCell(16).getStringCellValue());
           user.setAlias(nextRow.getCell(17).getStringCellValue());

           userlist.add(user);

       }

       return userlist;

   }*/

    public static void write2Excel(String excelFilePath, UserInfo user) throws Exception {

        FileInputStream input= new FileInputStream(new File(excelFilePath)); //Read the spreadsheet that needs to be updated
        XSSFWorkbook wb = new XSSFWorkbook(input); //Access the workbook
        XSSFSheet worksheet = wb.getSheetAt(0); //Access the worksheet, so that we can update / modify it.
        int rowCnt=worksheet.getLastRowNum();//get last row num
        Row newrow=worksheet.createRow(rowCnt+1);

        Cell cell;
        cell=newrow.createCell(0); cell.setCellValue(user.getId_gender());
        cell= newrow.createCell(1); cell.setCellValue(user.getFirstname());
        cell=newrow.createCell(2); cell.setCellValue(user.getLastname());
        cell=newrow.createCell(3); cell.setCellValue(user.getEmail());
        cell=newrow.createCell(4); cell.setCellValue(user.getPassword());
        cell=newrow.createCell(5); cell.setCellValue(user.getDays());
        cell=newrow.createCell(6); cell.setCellValue(user.getMonths());
        cell=newrow.createCell(7); cell.setCellValue(user.getYears());
        cell=newrow.createCell(8); cell.setCellValue(user.getCompany());
        cell=newrow.createCell(9); cell.setCellValue(user.getAddress1());
        cell=newrow.createCell(10); cell.setCellValue(user.getAddress2());
        cell=newrow.createCell(11); cell.setCellValue(user.getCity());
        cell=newrow.createCell(12); cell.setCellValue(user.getState());
        cell=newrow.createCell(13); cell.setCellValue(user.getPostcode());
        cell=newrow.createCell(14); cell.setCellValue(user.getOther());
        cell=newrow.createCell(15); cell.setCellValue(user.getPhone());
        cell=newrow.createCell(16); cell.setCellValue(user.getPhone_mobile());
        cell=newrow.createCell(17); cell.setCellValue(user.getAlias());

        input.close(); //Close the InputStream
        FileOutputStream output_file =new FileOutputStream(new File(excelFilePath));  //Open FileOutputStream to write updates
        wb.write(output_file); //write changes
        output_file.close();  //close the stream

    }


}
