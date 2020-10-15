package com.tyss.Bluestone1.generics;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Filelib {
	public String getExcelData(String sheetname,int row, int cell) throws EncryptedDocumentException, IOException, InvalidFormatException
	{
		FileInputStream f = new FileInputStream("./src/test/resources/data/Data.xlsx");
		Workbook w=WorkbookFactory.create(f);
		String data = w.getSheet(sheetname).getRow(row).getCell(cell).toString();
		return data;
	}
	
	public void setExcelData(String sheetname,int row, int cell,String setvalue) throws EncryptedDocumentException, IOException, InvalidFormatException
	{
		FileInputStream f = new FileInputStream("./data/Testscript1.xlsx");
		Workbook w=WorkbookFactory.create(f);
		w.getSheet(sheetname).getRow(row).getCell(cell).setCellValue(setvalue);
		FileOutputStream fo = new FileOutputStream("./data/Testscript1.xlsx");
		w.write(fo);
		w.close();
	}
}
