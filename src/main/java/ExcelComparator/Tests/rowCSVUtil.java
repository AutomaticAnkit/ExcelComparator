package ExcelComparator.Tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bouncycastle.jcajce.provider.symmetric.ARC4.Base;

import com.opencsv.CSVReaderBuilder;

public class rowCSVUtil {
	 static String user_dir = System.getProperty("user.dir");
		static String configPropertyFilePath = user_dir + "\\src\\test\\java\\SelniumPractice\\WebAutomation\\config.properties";
		static String envPropertyFilePath = user_dir+ "\\src\\test\\java\\SelniumPractice\\WebAutomation\\env.properties";
		
	//static String configPropertyFilePath = "C:\\Users\\ankit\\git\\repository\\ExcelComparator\\src\\test\\java\\SelniumPractice\\WebAutomation\\config.properties";
	//static String envPropertyFilePath = "C:\\Users\\ankit\\git\\repository\\ExcelComparator\\src\\test\\java\\SelniumPractice\\WebAutomation\\env.properties";
	private static Logger log=LogManager.getLogger(rowCSVUtil.class.getName());
	boolean result = true;
	static Properties prop = new Properties();
	static Properties envP = new Properties();
	static int prdt1NoOfCols;
	static int tempt1NoOfCols;
	static int tempt1NoOfRows;
	static String tempt1FirstColHeader;
	static String tempt1LastColHeader;	
	static int t2NoOfCols;
	static int prdt1NoOfRows;
	static int t2NoOfRows;
	static String prdt1FirstColHeader;
	static String prdt1LastColHeader;
	static String t2FirstColHeader;
	static String t2LastColHeader;
	static int t3NoOfCols;
	static int t3NoOfRows;
	static String t3LastColHeader;
	static String t3FirstColHeader;
	static int t4NoOfCols;
	static int t4NoOfRows;
	static String t4LastColHeader;
	static String t4FirstColHeader;
	static int t5NoOfCols;
	static int t5NoOfRows;
	static String t5LastColHeader;
	static String t5FirstColHeader;
	static String path;
	static int noOfTable;
	static StringBuffer finalData = new StringBuffer();

	
	
	 public rowCSVUtil() throws IOException {
		 System.out.println("---------------------------");
		 loadConfigFile();
		 System.out.println("---------------------------))))))))))))))))))))))))");
		 getConfigValue();
		 System.out.println("---------------------------************************");
		 
	 }
	public static String getValFromConfigPropFile(String Key) throws IOException {
		 try {
		 FileInputStream fis = new FileInputStream(configPropertyFilePath);
		 Properties prop = new Properties();
			prop.load(fis);
		 }catch (IOException e) {
		      System.out.println(e);
		    }
			String value=prop.getProperty(Key);
			return value;
		 
	 }
	 
	 public static String getValFromEnvPropFile(String Key) throws IOException {
		 try {
		 FileInputStream fis = new FileInputStream(envPropertyFilePath);
		 Properties envP = new Properties();
			envP.load(fis);
		 }catch (IOException e) {
		      System.out.println(e);
		    }
			String value=envP.getProperty(Key);
			return value;
		 
	 }
	 
	 public static void loadConfigFile() throws IOException {
		/* try {
		 FileInputStream fis = new FileInputStream(configPropertyFilePath);
			prop.load(fis);*/
			System.out.println("prop :" + prop.getProperty("noOfColumnsInTable1"));
			/*FileInputStream envPropfile = new FileInputStream(envPropertyFilePath);
			envP.load(envPropfile);
		 }catch (IOException e) {
		      System.out.println(e);
		    }*/
	 }

	 public static void getConfigValue()
	 {	
		prdt1NoOfCols = Integer.parseInt(splitValue(prop.getProperty("noOfColumnsInTable1"),1));
		tempt1NoOfCols=Integer.parseInt(splitValue(prop.getProperty("noOfColumnsInTable1"),2));
//		t2NoOfCols = Integer.parseInt(prop.getProperty("noOfColumnsInTable2"));
		prdt1NoOfRows = Integer.parseInt(splitValue(prop.getProperty("noOfRowsInTable1"),1));
		tempt1NoOfRows = Integer.parseInt(splitValue(prop.getProperty("noOfRowsInTable1"),2));
		
//		t2NoOfRows = Integer.parseInt(prop.getProperty("noOfRowsInTable2"));
		prdt1FirstColHeader = splitValue(prop.getProperty("firstColumnHeaderTable1"),1);
		prdt1LastColHeader = splitValue(prop.getProperty("LastColumnHeaderTable1"),1);
		tempt1FirstColHeader = splitValue(prop.getProperty("firstColumnHeaderTable1"),2);
		tempt1LastColHeader = splitValue(prop.getProperty("LastColumnHeaderTable1"),2);
//		t2FirstColHeader = prop.getProperty("firstColumnHeaderTable2");
//		t2LastColHeader = prop.getProperty("LastColumnHeaderTable2");
//		t3NoOfCols = Integer.parseInt(prop.getProperty("noOfColumnsInTable3"));
//		t3NoOfRows = Integer.parseInt(prop.getProperty("noOfRowsInTable3"));
//		t3LastColHeader = prop.getProperty("LastColumnHeaderTable3");
//		t3FirstColHeader = prop.getProperty("firstColumnHeaderTable3");
//		t4NoOfCols = Integer.parseInt(prop.getProperty("noOfColumnsInTable4"));
//		t4NoOfRows = Integer.parseInt(prop.getProperty("noOfRowsInTable4"));
//		t4LastColHeader = prop.getProperty("LastColumnHeaderTable4");
//		t4FirstColHeader = prop.getProperty("firstColumnHeaderTable4");
//		t5NoOfCols = Integer.parseInt(prop.getProperty("noOfColumnsInTable5"));
//		t5NoOfRows = Integer.parseInt(prop.getProperty("noOfRowsInTable5"));
//		t5LastColHeader = prop.getProperty("LastColumnHeaderTable5");
//		t5FirstColHeader = prop.getProperty("firstColumnHeaderTable5");
		path = envP.getProperty("folderPathforInputExcel");
		noOfTable = Integer.parseInt(prop.getProperty("totalTables"));
	 }
	 
	 private static String splitValue(String proName,int fileNo)
	 {
		 String result = null;
		 if(fileNo == 1)
		 {
			 result = (proName.split(","))[0];
		 }
		 else if(fileNo == 2)
		 {
			 result = (proName.split(","))[1];
		 }
		 return result;
	 }
	
		public static void rowStructureCheck(String fileName, int fileNo) throws IOException {
			for(int i=1;i<=noOfTable;i++) {
				
				System.out.println("i am in row struture check ");
					
						String[] fRow = (prop.getProperty("firstRowHeaderTable"+i)).split(",");
						String[] lRow = (prop.getProperty("LastRowHeaderTable"+i)).split(",");
						String[] rowCount = (prop.getProperty("noOfRowsInTable"+i)).split(",");
						String[] colCount = (prop.getProperty("noOfColumnsInTable"+i)).split(",");
				
								
						if(fileNo == 1)
						{
							System.out.println("File One :"+fileNo);
							masterCSVGenrator( fileName,fRow[0],lRow[0],
									Integer.parseInt(colCount[0]),Integer.parseInt(rowCount[0]),i);
							
							System.out.println("fROw[0],lRow[0],Integer.parseInt(colCount[0]),Integer.parseInt(rowCount[0])"+fRow[0]+lRow[0]+
									Integer.parseInt(colCount[0])+Integer.parseInt(rowCount[0]));
						}
						else
						{
							System.out.println("File two :"+fileNo);
						System.out.println("fRow[1],lRow[1],Integer.parseInt(colCount[1]),Integer.parseInt(rowCount[1])"+fRow[1]+lRow[1]+
								Integer.parseInt(colCount[1])+Integer.parseInt(rowCount[1]));
							masterCSVGenrator( fileName,fRow[1],lRow[1],
									Integer.parseInt(colCount[1]),Integer.parseInt(rowCount[1]),i);
							
						}
						
						
					
						
			}
		}
	public static void structureCheck(String fileName, int fileNo) throws IOException {
		for(int i=1;i<=noOfTable;i++) {
			
			System.out.println("i am in struture check ");
				
					String[] fColumn = (prop.getProperty("firstColumnHeaderTable"+i)).split(",");
					String[] lColumn = (prop.getProperty("LastColumnHeaderTable"+i)).split(",");
					String[] rowCount = (prop.getProperty("noOfRowsInTable"+i)).split(",");
					String[] colCount = (prop.getProperty("noOfColumnsInTable"+i)).split(",");
							
					if(fileNo == 1)
					{System.out.println("File One :"+fileNo);
						masterCSVGenrator( fileName,fColumn[0],lColumn[0],
								Integer.parseInt(colCount[0]),Integer.parseInt(rowCount[0]),i);
						
						System.out.println("fColumn[0],lColumn[0],Integer.parseInt(colCount[0]),Integer.parseInt(rowCount[0])"+fColumn[0]+lColumn[0]+
								Integer.parseInt(colCount[0])+Integer.parseInt(rowCount[0]));
					}
					else
					{System.out.println("File two :"+fileNo);
					System.out.println("fColumn[1],lColumn[1],Integer.parseInt(colCount[1]),Integer.parseInt(rowCount[1])"+fColumn[1]+lColumn[1]+
							Integer.parseInt(colCount[1])+Integer.parseInt(rowCount[1]));
						masterCSVGenrator( fileName,fColumn[1],lColumn[1],
								Integer.parseInt(colCount[1]),Integer.parseInt(rowCount[1]),i);
						
					}
					
					
				
					
		}
	}
	public static void masterCSVGenrator(String fileName, String fh, String lh, int nc, int nr, int tableNo) throws IOException {
		try {
		FileInputStream fis = new FileInputStream(configPropertyFilePath);
		Properties prop = new Properties();
		prop.load(fis);
		FileInputStream envPropfile = new FileInputStream(envPropertyFilePath);
		Properties envP = new Properties();
		envP.load(envPropfile);
		log.info("ENV file loaded");
		}catch (IOException e) {
		      System.out.println(e);
		      log.info("ENV file not loaded");
		    }
		test t = new test();
		String inputExcelFileName = fileName;
		System.out.println("fileName:" + fileName);
		StringBuffer table1 = tabletoStringGenrator(fileName, fh, lh, nc,nr,t.headerCompare(fileName,nc,fh,lh,tableNo));
		System.out.println("PFB, the table data from table.");
		System.out.println(table1 + "\n");
		
		/*
		 * 
		 * StringBuffer table2 = tabletoStringGenrator(fileName, fh, lh, nc,
		 * nr,t.headerCompare(nc,fh,lh));
		 * System.out.println("PFB, the table data from table 2.");
		 * System.out.println(table2 + "\n");
		 */
//
//		StringBuffer table3 = tabletoStringGenrator(inputExcelFileName, t3FirstColHeader, t3LastColHeader, t3NoOfCols,
//				t3NoOfRows);
//		System.out.println("PFB, the table data from table 3.");
//		System.out.println(table3 + "\n");
//		
//		StringBuffer table4 = tabletoStringGenrator(inputExcelFileName, t4FirstColHeader, t4LastColHeader, t4NoOfCols,
//				t4NoOfRows);
//		System.out.println("PFB, the table data from table 4.");
//		System.out.println(table4 + "\n");
//		
//		StringBuffer table5 = tabletoStringGenrator(inputExcelFileName, t5FirstColHeader, t5LastColHeader, t5NoOfCols,
//				t5NoOfRows);
//		System.out.println("PFB, the table data from table 5.");
//		System.out.println(table5 + "\n");


System.out.println("Final Data Value : " + finalData);
		finalData = finalData.append(table1);
//				.append(table3).append(table4).append(table5);
		System.out.println(finalData);
		System.out.println("CSV File generated on the Below Location : - ");
		System.out.println(path + inputExcelFileName + "\n");
		FileOutputStream fileOut = new FileOutputStream(path + inputExcelFileName + "CSV.csv");
		fileOut.write(finalData.toString().getBytes());
		fileOut.close();
		log.info("CSV created");
	}

	public static StringBuffer tabletoStringGenrator(String fileName, String firstHeader, String lastHeader,
			int numberOfCOlumns, int numberOfRows, ArrayList listOfIgnoreCols) throws IOException {
		try {
		FileInputStream fis = new FileInputStream(configPropertyFilePath);
		Properties prop = new Properties();
		prop.load(fis);
		FileInputStream envPropfile = new FileInputStream(envPropertyFilePath);
		Properties envP = new Properties();
		envP.load(envPropfile);
		String path = envP.getProperty("folderPathforInputExcel");
		}catch (IOException e) {
		      System.out.println(e);
		    }
		FileInputStream fileInStream = new FileInputStream(path + fileName + ".xlsx");
		int rowcount = 1;
		ArrayList ignoreColNo = new ArrayList();
		
		XSSFWorkbook workBook = new XSSFWorkbook(fileInStream);// Open the xlsx and get the requested sheet from the
												System.out.println("list recived"+listOfIgnoreCols);
												System.out.println("firstHeader"+firstHeader);
												System.out.println("lastHeader"+lastHeader);
												System.out.println("numberOfCOlumns"+numberOfCOlumns);
												System.out.println("numberOfRows"+numberOfRows);
												// workbook
		XSSFSheet s1 = workBook.getSheetAt(0);// Get Sheet from WorkBook
		StringBuffer csvLine = new StringBuffer();// String buffer to be written in CSV file
		int rc = s1.getLastRowNum();// Get last row number
//		System.out.println("Row count at the top is L : " + rc);
		for (int i = s1.getFirstRowNum(); i < rc; i++) {
			if (s1.getRow(i) != null) {
				int cc = s1.getRow(i).getLastCellNum();
				for (int j = 0; j < cc; j++) {
					System.out.println("Row count at the top is L : " + rc);
					
					
					
					if (s1.getRow(i).getCell(j) != null) {
//						System.out.println("showing ignore values"+listOfIgnoreCols.contains((s1.getRow(i).getCell(j).getStringCellValue())));
						System.out.println("below if s1.getRow(i).getCell(j)"+s1.getRow(i).getCell(j));
						
						System.out.println("numberOfCOlumns ; "+numberOfCOlumns);
						System.out.println("j value"+j);
						
						int temp = j;
						System.out.println("(s1.getRow(i).getCell(j + (numberOfCOlumns - 1))) : " + (s1.getRow(i).getCell(j + (numberOfCOlumns - 1))));
						if ((s1.getRow(i).getCell(j)) != null
								&& (s1.getRow(i).getCell(j + (numberOfCOlumns - 1))) != null) 
						{	
							System.out.println("below null condition");
							if(s1.getRow(i).getCell(j).getCellType()==s1.getRow(i).getCell(j).getCellType().NUMERIC) {
								System.out.println("below numeric  condition");
							}else
							{
								
									System.out.println("J value :"  + s1.getRow(i).getCell(j));
									System.out.println("firstHeader:" + firstHeader);
	
									System.out.println("lastHeader:" + lastHeader);
									
									if ((s1.getRow(i).getCell(j).getCellType() == s1.getRow(i).getCell(j).getCellType().NUMERIC)
											|| (s1.getRow(i).getCell(j + (numberOfCOlumns - 1)).getCellType() == s1.getRow(i)
													.getCell(j + (numberOfCOlumns - 1)).getCellType().NUMERIC)) 
									{
		
									}
									else if (((s1.getRow(i).getCell(j).getStringCellValue()).equals(firstHeader))
											&& ((s1.getRow(i).getCell(j + (numberOfCOlumns - 1)).getStringCellValue())
													.equals(lastHeader))) {
		//								System.out.println("I am here:");
										
										for (int k = 0; k < numberOfCOlumns;) {
											Cell c1 = s1.getRow(i).getCell(j);
											
											if (c1 != null && !(ignoreColNo.contains(k))) {
												System.out.println("list of ignore list111 : " + listOfIgnoreCols.size());
												
												switch (c1.getCellType()) {
												case STRING:
													System.out.println("I am inside of String block ");
													if(!(listOfIgnoreCols.contains((s1.getRow(i).getCell(j).getStringCellValue()))))
													{
														if(listOfIgnoreCols.size() > 0 && (listOfIgnoreCols.contains((s1.getRow(i).getCell(j).getStringCellValue()))))
														{
															
															if(k==0)
																ignoreColNo.add(200);
															else if(k==1)
																ignoreColNo.add(300);
															else if(k==2)
																ignoreColNo.add(400);
															else if(k==3)
																ignoreColNo.add(500);
															else if(k==4)
																ignoreColNo.add(600);
															
														}
														csvLine.append(c1.getStringCellValue() + ",");
													}
													else
													{
														if(listOfIgnoreCols.size() > 0)
														{
															listOfIgnoreCols.remove((s1.getRow(i).getCell(j).getStringCellValue()));
															if(k==0)
																ignoreColNo.add(200);
															else if(k==1)
																ignoreColNo.add(300);
															else if(k==2)
																ignoreColNo.add(400);
															else if(k==3)
																ignoreColNo.add(500);
															else if(k==4)
																ignoreColNo.add(600);
														}
													}
													break;
												case NUMERIC:
													System.out.println("ignoreColNo: " + ignoreColNo);
													if(k==0 && !ignoreColNo.contains(200))
													{
														System.out.println("c1.getNumericCellValue() : " + c1.getNumericCellValue());
														csvLine.append(c1.getNumericCellValue() + ",");
														
													}
													else if(k==1 && !ignoreColNo.contains(300))
													{
														System.out.println("c1.getNumericCellValue() : " + c1.getNumericCellValue());
														csvLine.append(c1.getNumericCellValue() + ",");
														
													}
													else if(k==2 && !ignoreColNo.contains(400))
													{
														System.out.println("c1.getNumericCellValue() : " + c1.getNumericCellValue());
														csvLine.append(c1.getNumericCellValue() + ",");
														
													}
													else if(k==3 && !ignoreColNo.contains(500))
													{
														System.out.println("c1.getNumericCellValue() : " + c1.getNumericCellValue());
														csvLine.append(c1.getNumericCellValue() + ",");
														
													}
													else if(k==4 && !ignoreColNo.contains(600))
													{
														System.out.println("c1.getNumericCellValue() : " + c1.getNumericCellValue());
														csvLine.append(c1.getNumericCellValue() + ",");
														
													}
													/*
													 * else { System.out.println("For checking"); ignoreColNo = 100; }
													 */
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
											else {
												System.out.println("is the condition met");
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
		}
//		System.out.println("====================AT the end =========================");
		return csvLine;
	}

	public static void csvComparison() throws IOException {
try {
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
		
		double var = 0;
		System.out.println("size of ist" + al1.size());
		for (int i = 0; i < al1.size();) {
			for (int x = 1; x <= noOfTables;) {				
				int tempColCount=tablecolumnCount(x,1);
				int y = i;
				System.out.println("Value of x : " + x);
				System.out.println("tempColCount: " + tempColCount);
				System.out.println("XXXXXXXXXXXXXXXXXXXXXXX"+splitValue((prop.getProperty("firstColumnHeaderTable" + x)),1));
				System.out.println("yyy"+al1.get(i));
				//System.out.println("uu"+al1.get(i + tempColCount - 1));
				if ((al1.get(i).equals(splitValue(prop.getProperty("firstColumnHeaderTable" + x),1))
						&& al1.get(i + tempColCount - 1).equals(splitValue(prop.getProperty("LastColumnHeaderTable" + x),1)))) 
				
				System.out.println("99999999999"+splitValue(prop.getProperty("firstColumnHeaderTable"+x),1) +"=="+al1.get(i));
//				System.out.println("100000000000"+splitValue(prop.getProperty("LastColumnHeaderTable" + x),1)+"=="+ al1.get(i + tempColCount - 1));
				if ((al1.get(i).equals(splitValue(prop.getProperty("firstColumnHeaderTable"+x),1))
						&& al1.get(i + tempColCount - 1).equals(splitValue(prop.getProperty("LastColumnHeaderTable" + x),1))))
				{
					System.out.println("i am in A2 A5 condition");
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
						System.out.println("al1.get(i)"+al1);
						System.out.println("al2.get(i)"+al2);
						System.out.println("i va;lue"+i);
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
				
				if (i < al1.size() && x < noOfTables) 
				{
					System.out.println("Value of I :" + i);
					System.out.println("Value of x :" + x);
					
					System.out.println("(prop.getProperty(\"firstColumnHeaderTable\" + (x + 1)): " + (prop.getProperty("firstColumnHeaderTable" + (x + 1))));
					if (al1.get(i).equals(splitValue(prop.getProperty("firstColumnHeaderTable" + (x + 1)),1))) {
						writer.append("\n");
						x++;
						System.out.println("££££££"+ al1.size());
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
}catch (IOException e) {
    System.out.println(e);
  }
	}

	public static void csvtoExcelCOnverion() throws IOException {
		// Data from CSV inserted into array
try {
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
		Sheet sheet = wb.createSheet("ProdVsUATDataComparison");		
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
			System.out.println("Value of X :" + x);
			System.out.println("csvRowAsStrng: " + csvRowAsStrng.size());
			
			line = csvRowAsStrng.get(x-1);
			System.out.println("(line.length : " + (line.length));
			Row row = sheet.createRow((short) r++);
			for (int i = 0; i < (line.length-1);) 
			{	
					row = sheet.createRow((short) r++);	
					if(i < (line.length-1))
					{
						rowCount = 1;
						int tmpColCount = tablecolumnCount(x,1);
						int tmpRowCount = tableRowCount(x,1);						
							if (line[i].equals(splitValue(prop.getProperty("firstColumnHeaderTable"+x),1))
								&& line[i + (tmpColCount - 1)].equals(splitValue(prop.getProperty("LastColumnHeaderTable"+x),1)))
							{	
								row = sheet.createRow((short) r++);
								
								for (int k = 0; k < tmpColCount;) 
								{
									if(x == csvRowAsStrng.size())
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
									else if(!line[i].equals(splitValue(prop.getProperty("firstColumnHeaderTable"+(x+1)),1)))
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
		String end = getDate()+ "_" + getTime();
		FileOutputStream fileOut = new FileOutputStream(finalExcelFolderPath + finalOutputExcelFile +end+".xlsx");
		wb.write(fileOut);
		fileOut.close();

		System.out.println("File Created sucessfully.");
}catch (IOException e) {
    System.out.println(e);
  }
	}
	
	
	  
	  private  final static String getDate(  )  
	  {
	        DateFormat df = new SimpleDateFormat( "dd-MM-yyyy" ) ;
	        df.setTimeZone( TimeZone.getTimeZone( "IST" )  ) ;
	        return ( df.format( new Date(  )  )  ) ;
	   }
	  
	 private  final static String getTime(  ) 
	 {
	        DateFormat df = new SimpleDateFormat( "hh-mm-ss" ) ;
	        //df.setTimeZone ( TimeZone.getTimeZone ( "PST" )  ) ;
	        df.setTimeZone( TimeZone.getTimeZone( "IST" )  ) ;
	         
	         
	        return ( df.format( new Date(  )  )  ) ;
	    }
		

	
private static int tableRowCount(int xLoop, int fileNo) throws IOException {
		
		FileInputStream fis = new FileInputStream(configPropertyFilePath);
		Properties prop = new Properties();
		prop.load(fis);
		int tRowCount = 0;
		int table1RowCount = Integer.parseInt((prop.getProperty("noOfRowsInTable1")).split(",")[fileNo]);
		int table2RowCount = Integer.parseInt((prop.getProperty("noOfRowsInTable2")).split(",")[fileNo]);
		int table3RowCount = Integer.parseInt((prop.getProperty("noOfRowsInTable3")).split(",")[fileNo]);
		int table4RowCount = Integer.parseInt((prop.getProperty("noOfRowsInTable4")).split(",")[fileNo]);
		int table5RowCount = Integer.parseInt((prop.getProperty("noOfRowsInTable5")).split(",")[fileNo]);
		
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
	private static int tablecolumnCount(int loopInt, int fileNo) throws IOException {
		System.out.println("I am in table count method");
		FileInputStream fis = new FileInputStream(configPropertyFilePath);
		Properties prop = new Properties();
		prop.load(fis);
		int tColCount = 0;
		System.out.println("value of x is "+loopInt);	
		int table1ColCount = Integer.parseInt(splitValue((prop.getProperty("noOfColumnsInTable1")),fileNo));
		//System.out.println("table count 1 is :"+Integer.parseInt((prop.getProperty("noOfColumnsInTable1")).split(",")[fileNo]));
		int table2ColCount = Integer.parseInt(splitValue((prop.getProperty("noOfColumnsInTable2")),fileNo));
		int table3ColCount = Integer.parseInt(splitValue((prop.getProperty("noOfColumnsInTable3")),fileNo));
		int table4ColCount = Integer.parseInt(splitValue((prop.getProperty("noOfColumnsInTable4")),fileNo));
		int table5ColCount = Integer.parseInt(splitValue((prop.getProperty("noOfColumnsInTable5")),fileNo));
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
		System.out.println("tColCount:" + tColCount);
		return tColCount;
	}

	private static String calculateWRTVariance(double itemList1, double itemList2, double var) {
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
	
	private static double varianceCalculator(int tableColumnCount, int iloop, int tableNumber) throws IOException {
		
		FileInputStream fis = new FileInputStream(configPropertyFilePath);
		Properties prop = new Properties();
		prop.load(fis);
		double VarianceCol1 = 0;
		double VarianceCol2 = 0;
		double VarianceCol3 = 0;
		double VarianceCol4 = 0;
		double VarianceCol5 = 0;
		
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
				
		double variance = 0;
		
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

	

// public static boolean structureVerification(String colHeader) {
//	 System.out.println(colHeader);
//		String[] test = colHeader.split(",");
//		System.out.println(test[0]);
//		System.out.println("this is integer");
//		if(test[0].equalsIgnoreCase(test[1])) {
//			
//			return true;
//		}else
//		{
//			return false;
//		}
//	 
//	
//	 
// }
 
 
}
