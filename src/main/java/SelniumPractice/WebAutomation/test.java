package SelniumPractice.WebAutomation;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class test {
	
	static String configPropertyFilePath = "C:\\Users\\ankit\\git\\repository\\ExcelComparator\\src\\test\\java\\SelniumPractice\\WebAutomation\\config.properties";
	static String envPropertyFilePath = "C:\\Users\\ankit\\git\\repository\\ExcelComparator\\src\\test\\java\\SelniumPractice\\WebAutomation\\env.properties";
	static ArrayList prodColHeaderT1 = new ArrayList();
	static ArrayList prodColHeaderT2 = new ArrayList();
	static ArrayList prodColHeaderT3 = new ArrayList();
	static ArrayList prodColHeaderT4 = new ArrayList();
	static ArrayList prodColHeaderT5 = new ArrayList();
	
	public static void main(String[] args) throws IOException {

//		ArrayList colHeader = getColHeader("ProdData",6, "A1", "A6");
//		System.out.println(colHeader);
//		System.out.println(headerCompare());
	}
	
	public static ArrayList headerCompare(String fileName, int nc, String fh, String lh, int tableNo)throws IOException {
		
		ArrayList tempColHeader = new ArrayList();
		if(fileName.equalsIgnoreCase("ProdData"))
		{
			if(tableNo == 1)
				prodColHeaderT1 = getColHeader(fileName, nc, fh, lh);
			else if(tableNo == 2)
				prodColHeaderT2 = getColHeader(fileName, nc, fh, lh);
			else if(tableNo == 3)
				prodColHeaderT3 = getColHeader(fileName, nc, fh, lh);
			else if(tableNo == 4)
				prodColHeaderT4 = getColHeader(fileName, nc, fh, lh);
			else if(tableNo == 5)
				prodColHeaderT5 = getColHeader(fileName, nc, fh, lh);
			
			System.out.println("prodColHeaderT2:  "+ prodColHeaderT2);
			System.out.println("prodColHeaderT1:  "+ prodColHeaderT1);
		}
		else
		{
		
			System.out.println("prodColHeaderT2:  "+ prodColHeaderT2);
			System.out.println("prodColHeaderT1:  "+ prodColHeaderT1);
			tempColHeader = getColHeader(fileName, nc, fh, lh);
			
			
			System.out.println("tempColHeader:  "+ tempColHeader);
			
			if(tableNo == 1)
				tempColHeader.removeAll(prodColHeaderT1);
			else if(tableNo == 2)
				tempColHeader.removeAll(prodColHeaderT2);
			else if(tableNo == 3)
				tempColHeader.removeAll(prodColHeaderT3);
			else if(tableNo == 4)
				tempColHeader.removeAll(prodColHeaderT4);
			else if(tableNo == 5)
				tempColHeader.removeAll(prodColHeaderT5);
			
			System.out.println("tempColHeader:  "+ tempColHeader);
		}
		return tempColHeader;
	}
	
	
	public static ArrayList getColHeader( String fileName,int colCount , String firstHeader , String lastHeader) throws IOException{
		
		ArrayList ClmHdrsPrd = new ArrayList();
		String path= "C:\\Users\\ankit\\Desktop\\Excel\\";
		// TODO Auto-generated method stub
		FileInputStream fis=new FileInputStream(path+fileName+".xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		ArrayList<String> al1= new ArrayList<>();
		XSSFSheet s=wb.getSheetAt(0);		
		int noOfRows=s.getLastRowNum();
		for(int i=s.getFirstRowNum();i<noOfRows;i++) {
			if(s.getRow(i) != null) {
			int noOfCells=s.getRow(i).getLastCellNum();
			for(int j=0;j<noOfCells;j++) {
				Cell cell=s.getRow(i).getCell(j);
				if(cell != null) {
					if(cell.getCellType()==cell.getCellType().NUMERIC) 
					{
//						System.out.println(cell.getNumericCellValue());
					}
					else if(cell.getStringCellValue().equals(firstHeader)) 
					{
						if(s.getRow(i).getCell(j+(colCount-1)).getStringCellValue().equalsIgnoreCase(lastHeader))
						{
							for(int k=j;k<colCount+j;k++) {
									ClmHdrsPrd.add(s.getRow(i).getCell(k).getStringCellValue());
							}
							
						}
					}
					else
					{
						
					}
				}
			}
		}
	}
		return ClmHdrsPrd;

	}

}
