package SelniumPractice.WebAutomation;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class test extends rowCSVUtil {
	private static Logger log = LogManager.getLogger(test.class.getName());
	
	public test() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}


	static String configPropertyFilePath = "C:\\Users\\ankit\\git\\repository\\ExcelComparator\\src\\test\\java\\SelniumPractice\\WebAutomation\\config.properties";
	static String envPropertyFilePath = "C:\\Users\\ankit\\git\\repository\\ExcelComparator\\src\\test\\java\\SelniumPractice\\WebAutomation\\env.properties";
	static ArrayList prodColHeaderT1 = new ArrayList();
	static ArrayList prodColHeaderT2 = new ArrayList();
	static ArrayList prodColHeaderT3 = new ArrayList();
	static ArrayList prodColHeaderT4 = new ArrayList();
	static ArrayList prodColHeaderT5 = new ArrayList();
	
	
	static ArrayList prodRowHeaderT1 = new ArrayList();
	static ArrayList prodRowHeaderT2  = new ArrayList();
	static ArrayList prodRowHeaderT3 = new ArrayList();
	static ArrayList prodRowHeaderT4 = new ArrayList();
	static ArrayList prodRowHeaderT5  = new ArrayList();
	
	public static void main(String[] args) throws IOException {
	
//		getValFromConfigPropFile(configPropertyFilePath);
		System.out.println(getValFromEnvPropFile("inputExcelFileName"));
//		getRowHeader( "ProdData", 5 ,  "A1", "R1" , "R5");
	}
	
	public static ArrayList rowCompare(String fileName, int nr, String fr, String lr, int tableNo)throws IOException {
		
		ArrayList tempRowHeader = new ArrayList();
		if(fileName.equalsIgnoreCase(getValFromEnvPropFile("inputExcelFileName")))
		{
			if(tableNo == 1)
				prodColHeaderT1 = getColHeader(fileName, nr, fr, lr);
			else if(tableNo == 2)
				prodColHeaderT2 = getColHeader(fileName,nr, fr, lr);
			else if(tableNo == 3)
				prodColHeaderT3 = getColHeader(fileName, nr, fr, lr);
			else if(tableNo == 4)
				prodColHeaderT4 = getColHeader(fileName, nr, fr, lr);
			else if(tableNo == 5)
				prodColHeaderT5 = getColHeader(fileName,nr, fr, lr);
			
			System.out.println("prodColHeaderT2:  "+ prodColHeaderT2);
			System.out.println("prodColHeaderT1:  "+ prodColHeaderT1);
		}
		else
		{
		
			System.out.println("prodColHeaderT2:  "+ prodColHeaderT2);
			System.out.println("prodColHeaderT1:  "+ prodColHeaderT1);
			tempRowHeader = getColHeader(fileName, nr, fr, lr);
			
			
			System.out.println("tempRowHeader:  "+ tempRowHeader);
			
			if(tableNo == 1)
				tempRowHeader.removeAll(prodColHeaderT1);
			else if(tableNo == 2)
				tempRowHeader.removeAll(prodColHeaderT2);
			else if(tableNo == 3)
				tempRowHeader.removeAll(prodColHeaderT3);
			else if(tableNo == 4)
				tempRowHeader.removeAll(prodColHeaderT4);
			else if(tableNo == 5)
				tempRowHeader.removeAll(prodColHeaderT5);
			
			System.out.println("tempRowHeader:  "+ tempRowHeader);
		}
		return tempRowHeader;
	}
	
public static ArrayList getRowHeader( String fileName,int rowCount , String firstCHeader, String firstRHeader , String lastRHeader) throws IOException
{
	
		
		ArrayList ClmHdrsPrd = new ArrayList();
//		String path= "C:\\Users\\ankit\\Desktop\\Excel\\";
		// TODO Auto-generated method stub
		FileInputStream fis=new FileInputStream(getValFromEnvPropFile("folderPathforInputExcel")+getValFromEnvPropFile("inputExcelFileName")+".xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		ArrayList<String> al1= new ArrayList<>();
		XSSFSheet s=wb.getSheetAt(0);		
		int noOfRows=s.getLastRowNum();
		for(int i=s.getFirstRowNum();i<noOfRows;i++) {
			if(s.getRow(i) != null) {
			int noOfCells = s.getRow(i).getLastCellNum();
			for(int j=0;j<noOfCells;j++) {
				Cell cell=s.getRow(i).getCell(j);
				if(cell != null) {
						if(cell.getCellType()==cell.getCellType().NUMERIC) 
						{
	//						System.out.println(cell.getNumericCellValue());
						}
						else if(cell.getStringCellValue().equals(firstCHeader)) 
						{
							
						}
						else
						{
								System.out.println("cell1"+cell);
								
						}
						
					}
				}
			}
	}
		
		return ClmHdrsPrd;
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
				}
			}
		}
	}
		return ClmHdrsPrd;

	}

}
