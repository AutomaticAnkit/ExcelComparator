package SelniumPractice.WebAutomation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.opencsv.CSVReaderBuilder;

public class csvUtils {
	static String configPropertyFilePath = "C:\\Users\\ankit\\git\\repository\\ExcelComparator\\src\\test\\java\\SelniumPractice\\WebAutomation\\config.properties";
	static String envPropertyFilePath = "C:\\Users\\ankit\\git\\repository\\ExcelComparator\\src\\test\\java\\SelniumPractice\\WebAutomation\\env.properties";

	public static void masterCSVGenrator(String fileName) throws IOException {

		FileInputStream fis = new FileInputStream(configPropertyFilePath);
		Properties prop = new Properties();
		prop.load(fis);
		FileInputStream envPropfile = new FileInputStream(envPropertyFilePath);
		Properties envP = new Properties();
		envP.load(envPropfile);
		int t1NoOfCols = Integer.parseInt(prop.getProperty("noOfColumnsInTable1"));
		int t2NoOfCols = Integer.parseInt(prop.getProperty("noOfColumnsInTable2"));
		int t1NoOfRows = Integer.parseInt(prop.getProperty("noOfRowsInTable1"));
		int t2NoOfRows = Integer.parseInt(prop.getProperty("noOfRowsInTable2"));
		String t1FirstColHeader = prop.getProperty("firstColumnHeaderTable1");
		String t1LastColHeader = prop.getProperty("LastColumnHeaderTable1");
		String t2FirstColHeader = prop.getProperty("firstColumnHeaderTable2");
		String t2LastColHeader = prop.getProperty("LastColumnHeaderTable2");
		int t3NoOfCols = Integer.parseInt(prop.getProperty("noOfColumnsInTable3"));
		int t3NoOfRows = Integer.parseInt(prop.getProperty("noOfRowsInTable3"));
		String t3LastColHeader = prop.getProperty("LastColumnHeaderTable3");
		String t3FirstColHeader = prop.getProperty("firstColumnHeaderTable3");
		int t4NoOfCols = Integer.parseInt(prop.getProperty("noOfColumnsInTable4"));
		int t4NoOfRows = Integer.parseInt(prop.getProperty("noOfRowsInTable4"));
		String t4LastColHeader = prop.getProperty("LastColumnHeaderTable4");
		String t4FirstColHeader = prop.getProperty("firstColumnHeaderTable4");
		int t5NoOfCols = Integer.parseInt(prop.getProperty("noOfColumnsInTable5"));
		int t5NoOfRows = Integer.parseInt(prop.getProperty("noOfRowsInTable5"));
		String t5LastColHeader = prop.getProperty("LastColumnHeaderTable5");
		String t5FirstColHeader = prop.getProperty("firstColumnHeaderTable5");
		String path = envP.getProperty("folderPathforInputExcel");
		String inputExcelFileName = fileName;

		StringBuffer table1 = tabletoStringGenrator(inputExcelFileName, t1FirstColHeader, t1LastColHeader, t1NoOfCols,
				t1NoOfRows);
		System.out.println("PFB, the table data from table 1.");
		System.out.println(table1 + "\n");

		StringBuffer table2 = tabletoStringGenrator(inputExcelFileName, t2FirstColHeader, t2LastColHeader, t2NoOfCols,
				t2NoOfRows);
		System.out.println("PFB, the table data from table 2.");
		System.out.println(table2 + "\n");

		StringBuffer table3 = tabletoStringGenrator(inputExcelFileName, t3FirstColHeader, t3LastColHeader, t3NoOfCols,
				t3NoOfRows);
		System.out.println("PFB, the table data from table 3.");
		System.out.println(table3 + "\n");
		
		StringBuffer table4 = tabletoStringGenrator(inputExcelFileName, t4FirstColHeader, t4LastColHeader, t4NoOfCols,
				t4NoOfRows);
		System.out.println("PFB, the table data from table 4.");
		System.out.println(table4 + "\n");
		
		StringBuffer table5 = tabletoStringGenrator(inputExcelFileName, t5FirstColHeader, t5LastColHeader, t5NoOfCols,
				t5NoOfRows);
		System.out.println("PFB, the table data from table 5.");
		System.out.println(table5 + "\n");


		StringBuffer finalData = table1.append(table2).append(table3).append(table4).append(table5);
		System.out.println(finalData);
		System.out.println("CSV File generated on the Below Location : - ");
		System.out.println(path + inputExcelFileName + "\n");
		FileOutputStream fileOut = new FileOutputStream(path + inputExcelFileName + "CSV.csv");
		fileOut.write(finalData.toString().getBytes());
		fileOut.close();
	}

	public static StringBuffer tabletoStringGenrator(String fileName, String firstHeader, String lastHeader,
			int numberOfCOlumns, int numberOfRows) throws IOException {

		FileInputStream fis = new FileInputStream(configPropertyFilePath);
		Properties prop = new Properties();
		prop.load(fis);
		FileInputStream envPropfile = new FileInputStream(envPropertyFilePath);
		Properties envP = new Properties();
		envP.load(envPropfile);
		String path = envP.getProperty("folderPathforInputExcel");
		FileInputStream fileInStream = new FileInputStream(path + fileName + ".xlsx");
		int rowcount = 1;
		XSSFWorkbook workBook = new XSSFWorkbook(fileInStream);// Open the xlsx and get the requested sheet from the
																// workbook
		XSSFSheet s1 = workBook.getSheetAt(0);// Get Sheet from WorkBook
		StringBuffer csvLine = new StringBuffer();// String buffer to be written in CSV file
		int rc = s1.getLastRowNum();// Get last row number
//		System.out.println("Row count at the top is L : " + rc);
		for (int i = s1.getFirstRowNum(); i < rc; i++) {
			if (s1.getRow(i) != null) {
				int cc = s1.getRow(i).getLastCellNum();
				for (int j = 0; j < cc; j++) {
//					System.out.println("Row count at the top is L : " + rc);
					if (s1.getRow(i).getCell(j) != null) {
						int temp = j;
						if ((s1.getRow(i).getCell(j)) != null
								&& (s1.getRow(i).getCell(j + (numberOfCOlumns - 1))) != null) {
							if ((s1.getRow(i).getCell(j).getCellType() == s1.getRow(i).getCell(j).getCellType().NUMERIC)
									|| (s1.getRow(i).getCell(j + (numberOfCOlumns - 1)).getCellType() == s1.getRow(i)
											.getCell(j + (numberOfCOlumns - 1)).getCellType().NUMERIC)) {

							} else if (((s1.getRow(i).getCell(j).getStringCellValue()).equals(firstHeader))
									&& ((s1.getRow(i).getCell(j + (numberOfCOlumns - 1)).getStringCellValue())
											.equals(lastHeader))) {
//								System.out.println("I am here:");
								for (int k = 0; k < numberOfCOlumns;) {
									Cell c1 = s1.getRow(i).getCell(j);
									if (c1 != null) {
										switch (c1.getCellType()) {
										case STRING:
											csvLine.append(c1.getStringCellValue() + ",");
											break;
										case NUMERIC:
											csvLine.append(c1.getNumericCellValue() + ",");
											break;
										case BOOLEAN:
											csvLine.append(c1.getBooleanCellValue() + ",");
											break;
										case _NONE:
											break;

										case BLANK:
											break;

										default:
											break;
										}
									}
									k++;
									j++;
									if (k % numberOfCOlumns == 0 && rowcount != numberOfRows) {
										rowcount++;
										k = 0;
										j = temp;
										i++;
									}
								}
								break;
							}
						}
					}
				}
			}
		}
//		System.out.println("====================AT the end =========================");
		return csvLine;
	}

	public static void csvComparison() throws IOException {

		FileInputStream envPropfile = new FileInputStream(envPropertyFilePath);
		Properties envP = new Properties();
		envP.load(envPropfile);
		String path = envP.getProperty("folderPathforInputExcel");
		String file1 = envP.getProperty("inputExcelFileName") + "CSV.csv";
		String file2 = envP.getProperty("inputExcelTemplate") + "CSV.csv";
		String file3 = "Book4CSV.csv";
		ArrayList al1 = new ArrayList();
		ArrayList al2 = new ArrayList();
		FileInputStream fis = new FileInputStream(configPropertyFilePath);
		Properties prop = new Properties();
		prop.load(fis);
		int noOfTables=Integer.parseInt(prop.getProperty("totalTables"));
		FileWriter writer = new FileWriter(path + file3);
		BufferedReader CSVFile1 = new BufferedReader(new FileReader(path + file1));
		String dataRow1 = CSVFile1.readLine();
		while (dataRow1 != null) {
			String[] dataArray1 = dataRow1.split(",");
			for (String item1 : dataArray1) {
				al1.add(item1);
			}
			dataRow1 = CSVFile1.readLine(); // Read next line of data.
		}
		CSVFile1.close();

		BufferedReader CSVFile2 = new BufferedReader(new FileReader(path + file2));
		String dataRow2 = CSVFile2.readLine();
		while (dataRow2 != null) {
			String[] dataArray2 = dataRow2.split(",");
			for (String item2 : dataArray2) {
				al2.add(item2);
			}
			dataRow2 = CSVFile2.readLine(); // Read next line of data.
		}
		CSVFile2.close();
		boolean tableFlag = false;
		
		int var = 0;
		System.out.println("size of ist" + al1.size());
		for (int i = 0; i < al1.size();) {
			for (int x = 1; x <= noOfTables;) {				
				int tempColCount=tablecolumnCount(x);
				int y = i;
				if ((al1.get(i).equals(prop.getProperty("firstColumnHeaderTable" + x))
						&& al1.get(i + tempColCount - 1).equals(prop.getProperty("LastColumnHeaderTable" + x)))) {
					for (int k = i; k < tempColCount + y;) {
						writer.append("" + al1.get(k));
						writer.append(",");
						k++;
						i++;
					}
				} else if (al1.get(i).equals(al2.get(i))) {
					System.out.println(al1.get(i) + " == " + al2.get(i));
					writer.append("" + "Pass");
					writer.append(",");
					i++;
				} else {					
					var=varianceCalculator(tempColCount,i,x);
					if (envP.getProperty("runWithTol").equalsIgnoreCase("Yes")) 
					{
						double itemList1 = Double.parseDouble((String) al1.get(i));
						double itemList2 = Double.parseDouble((String) al2.get(i));
						String result = calculateWRTVariance(itemList1, itemList2, var);
						writer.append("" + result);
						writer.append(",");
						i++;
					} else {
						System.out.println(al1.get(i) + " != " + al2.get(i));
						writer.append("" + "Fail");
						writer.append(",");
						
						i++;
					}
				}
				if (i < al1.size()) 
				{
					if (al1.get(i).equals(prop.getProperty("firstColumnHeaderTable" + (x + 1)))) {
						writer.append("\n");
						x++;
					}
				} else 
				{
					break;
				}
			}
		}

		writer.flush();
		writer.close();
		System.out.println("File Created Successfully.");
		System.out.println("PLease Check the File on Below Location");
		System.out.println(path + "\\" + file3);
		for (Object bs : al2) {
			al1.remove(bs);
		}
		int size = al1.size();
		System.out.println("Number of Values found diff are  " + size);
		System.out.println(" ");

	}

	public static void csvtoExcelCOnverion() throws IOException {
		// Data from CSV inserted into array

		FileInputStream fis = new FileInputStream(configPropertyFilePath);
		Properties prop = new Properties();
		prop.load(fis);
		FileInputStream envPropfile = new FileInputStream(envPropertyFilePath);
		Properties envP = new Properties();
		envP.load(envPropfile);
		String finalExcelFolderPath = envP.getProperty("finalExcelFolderPath");
		String finalOutputExcelFile = envP.getProperty("finalOutputExcelFile");
		String[] line;
		int r = 0;// Row increment
		int noOfTables = Integer.parseInt(prop.getProperty("totalTables"));
		int rowCount = 1;
		Workbook wb = new HSSFWorkbook();
		CreationHelper helper = wb.getCreationHelper();
		Sheet sheet = wb.createSheet("new sheet");		
		HSSFCellStyle style = (HSSFCellStyle) wb.createCellStyle();// Border for Cell
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setShrinkToFit(true);
		
		CSVReaderBuilder reader = new CSVReaderBuilder(
				new FileReader("C:\\Users\\ankit\\Desktop\\Excel\\Book4CSV.csv"));// CSV file reader
		List<String[]> csvRowAsStrng = reader.build().readAll();		
		for(int x=1;x<=csvRowAsStrng.size();x++) 
		{
			line = csvRowAsStrng.get(x-1);
			Row row = sheet.createRow((short) r++);
			for (int i = 0; i < (line.length-1);) 
			{	
					row = sheet.createRow((short) r++);	
					if(i < (line.length-1))
					{
						rowCount = 1;
						int tmpColCount = tablecolumnCount(x);
						int tmpRowCount = tableRowCount(x);						
							if (line[i].equals(prop.getProperty("firstColumnHeaderTable"+x))
								&& line[i + (tmpColCount - 1)].equals(prop.getProperty("LastColumnHeaderTable"+x)))
							{	
							row = sheet.createRow((short) r++);
								
								for (int k = 0; k < tmpColCount;) 
								{
									if (!line[i].equals(prop.getProperty("firstColumnHeaderTable"+(x+1))) )
									{
										Cell cell = row.createCell(k);
										cell.setCellStyle(style);
										cell.setCellValue(helper.createRichTextString(line[i]));
										k++;
										i++;
										if (i % tmpColCount == 0 && rowCount != tmpRowCount)
										{
											rowCount++;
											k = 0;
											row = sheet.createRow((short) r++);
										}
										
									}
								}
							}
							else {
								break;
							}
					}else {
						break;
					}
				//}
				
			}	
			
		}
		FileOutputStream fileOut = new FileOutputStream(finalExcelFolderPath + finalOutputExcelFile +".xlsx");
		wb.write(fileOut);
		fileOut.close();

		System.out.println("File Created sucessfully.");
	}
		

	
private static int tableRowCount(int xLoop) throws IOException {
		
		FileInputStream fis = new FileInputStream(configPropertyFilePath);
		Properties prop = new Properties();
		prop.load(fis);
		int tRowCount = 0;
		int table1RowCount = Integer.parseInt(prop.getProperty("noOfRowsInTable1"));
		int table2RowCount = Integer.parseInt(prop.getProperty("noOfRowsInTable2"));
		int table3RowCount = Integer.parseInt(prop.getProperty("noOfRowsInTable3"));
		int table4RowCount = Integer.parseInt(prop.getProperty("noOfRowsInTable4"));
		int table5RowCount = Integer.parseInt(prop.getProperty("noOfRowsInTable5"));
		
		if (xLoop == 1) {
			tRowCount = table1RowCount;
		}

		if (xLoop == 2) {
			tRowCount = table2RowCount;
		}

		if (xLoop == 3) {
			tRowCount = table3RowCount;
		}
		if (xLoop == 4) {
			
			tRowCount = table4RowCount;
		}
		if (xLoop == 5) {
			
			tRowCount = table5RowCount;
		}
		return tRowCount;
	}
	private static int tablecolumnCount(int loopInt) throws IOException {
		
		FileInputStream fis = new FileInputStream(configPropertyFilePath);
		Properties prop = new Properties();
		prop.load(fis);
		int tColCount = 0;
		int table1ColCount = Integer.parseInt(prop.getProperty("noOfColumnsInTable1"));
		int table2ColCount = Integer.parseInt(prop.getProperty("noOfColumnsInTable2"));
		int table3ColCount = Integer.parseInt(prop.getProperty("noOfColumnsInTable3"));
		int table4ColCount = Integer.parseInt(prop.getProperty("noOfColumnsInTable4"));
		int table5ColCount = Integer.parseInt(prop.getProperty("noOfColumnsInTable5"));
		/*
		 * System.out.println("table1ColCount:" + table1ColCount);
		 * System.out.println("table2ColCount:" + table2ColCount);
		 */
		if (loopInt == 1) {
			tColCount = table1ColCount;
		}

		if (loopInt == 2) {
			tColCount = table2ColCount;
		}

		if (loopInt == 3) {
			tColCount = table3ColCount;
		}
		if (loopInt == 4) {
			tColCount = table4ColCount;
		}
		if (loopInt == 5) {
			tColCount = table5ColCount;
		}
		//System.out.println("tColCount:" + tColCount);
		return tColCount;
	}

	private static String calculateWRTVariance(double itemList1, double itemList2, int var) {
		String result = "";

		if (itemList1 < itemList2) {
			if (itemList1 + var == itemList2) {
				result = "Pass with Variance: " + var;
			} else {
				result = "Fail";
			}
		} else {
			if (itemList1 - var == itemList2) {
				result = "Pass with Variance: " + var;
			} else {
				result = "Fail";
			}
		}
		return result;
	}
	
	private static int varianceCalculator(int tableColumnCount, int iloop, int tableNumber) throws IOException {
		
		FileInputStream fis = new FileInputStream(configPropertyFilePath);
		Properties prop = new Properties();
		prop.load(fis);
		int VarianceCol1 = 0;
		int VarianceCol2 = 0;
		int VarianceCol3 = 0;
		int VarianceCol4 = 0;
		int VarianceCol5 = 0;
		
		switch(tableColumnCount) {
		case 1:
			VarianceCol1 = Integer.parseInt(prop.getProperty("tolranceValueTable"+tableNumber+"Col1"));
			break;
		case 2:
			VarianceCol1 = Integer.parseInt(prop.getProperty("tolranceValueTable"+tableNumber+"Col1"));
			VarianceCol2 = Integer.parseInt(prop.getProperty("tolranceValueTable"+tableNumber+"Col2"));
			break;
		case 3:
			VarianceCol1 = Integer.parseInt(prop.getProperty("tolranceValueTable"+tableNumber+"Col1"));
			VarianceCol2 = Integer.parseInt(prop.getProperty("tolranceValueTable"+tableNumber+"Col2"));
			VarianceCol3 = Integer.parseInt(prop.getProperty("tolranceValueTable"+tableNumber+"Col3"));
			break;
		case 4:
			VarianceCol1 = Integer.parseInt(prop.getProperty("tolranceValueTable"+tableNumber+"Col1"));
			VarianceCol2 = Integer.parseInt(prop.getProperty("tolranceValueTable"+tableNumber+"Col2"));
			VarianceCol3 = Integer.parseInt(prop.getProperty("tolranceValueTable"+tableNumber+"Col3"));
			VarianceCol4 = Integer.parseInt(prop.getProperty("tolranceValueTable"+tableNumber+"Col4"));
			break;
		case 5:
			VarianceCol1 = Integer.parseInt(prop.getProperty("tolranceValueTable"+tableNumber+"Col1"));
			VarianceCol2 = Integer.parseInt(prop.getProperty("tolranceValueTable"+tableNumber+"Col2"));
			VarianceCol3 = Integer.parseInt(prop.getProperty("tolranceValueTable"+tableNumber+"Col3"));
			VarianceCol4 = Integer.parseInt(prop.getProperty("tolranceValueTable"+tableNumber+"Col4"));
			VarianceCol5 = Integer.parseInt(prop.getProperty("tolranceValueTable"+tableNumber+"Col5"));
			break;
		}
				
		int variance = 0;
		
		switch(tableColumnCount) {
		case 5:
			if (iloop % tableColumnCount == 0) {
				variance = VarianceCol1;
			} else if (iloop % tableColumnCount == 1) {
				variance = VarianceCol2;
			} else if (iloop % tableColumnCount == 2) {
				variance = VarianceCol3;
			} else if (iloop % tableColumnCount == 3) {
				variance = VarianceCol4;
			} else if (iloop % tableColumnCount == 4) {
				variance = VarianceCol5;
			}
			break;
			
		case 4:
			if (iloop % tableColumnCount == 3) {
				variance = VarianceCol1;
				System.out.println("iloop : " + iloop);
				System.out.println("variance : " + variance);
				System.out.println("VarianceCol1 : " + VarianceCol1);
			} else if (iloop % tableColumnCount == 0) {
				variance = VarianceCol2;
				System.out.println("iloop : " + iloop);
				System.out.println("variance : " + variance);
				System.out.println("VarianceCol2 : " + VarianceCol2);
			} else if (iloop % tableColumnCount == 1) {
				variance = VarianceCol3;
				System.out.println("iloop : " + iloop);
				System.out.println("variance : " + variance);
				System.out.println("VarianceCol3 : " + VarianceCol3);
			} else if (iloop % tableColumnCount == 2) {
				variance = VarianceCol4;
				System.out.println("iloop : " + iloop);
				System.out.println("variance : " + variance);
				System.out.println("VarianceCol4 : " + VarianceCol4);
			}
			break;
			
		case 3:
			if (iloop % tableColumnCount == 3) {
				variance = VarianceCol1;
			} else if (iloop % tableColumnCount == 0) {
				variance = VarianceCol2;
			} else if (iloop % tableColumnCount == 1) {
				variance = VarianceCol3;
			}
			break;
		
		case 2:
			if (iloop % tableColumnCount == 0) {
				variance = VarianceCol1;
			} else if (iloop % tableColumnCount == 1) {
				variance = VarianceCol2;
			}
			break;
			
		case 1:
			if (iloop % tableColumnCount == 0) {
				variance = VarianceCol1;
			}
			break;
	}
		return variance;
	}


}
