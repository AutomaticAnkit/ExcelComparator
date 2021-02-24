package SelniumPractice.WebAutomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExcelcsvComp extends csvUtils{
	public static Logger log = LogManager.getLogger(ExcelcsvComp.class.getName());
	
	public ExcelcsvComp() throws IOException {
		
		super();
		 
	}
	static long startTime = System.currentTimeMillis();
	
	
	
	public static void main(String[] args) throws Exception  {	
		try
		{
		
		log.info("started file converstion");
		//Converts Excel to CSV file as per the parameters provided in Config file		
//		String configPropertyFilePath = "C:\\Users\\ankit\\git\\repository\\ExcelComparator\\src\\test\\java\\SelniumPractice\\WebAutomation\\config.properties";
//		String envPropertyFilePath = "C:\\Users\\ankit\\git\\repository\\ExcelComparator\\src\\test\\java\\SelniumPractice\\WebAutomation\\env.properties";
//		FileInputStream fis = new FileInputStream(configPropertyFilePath);
//		Properties prop = new Properties();
//		prop.load(fis);
//		FileInputStream envPropfile = new FileInputStream(envPropertyFilePath);
//		Properties envP = new Properties();
//		envP.load(envPropfile);		
		csvUtils obj1 = new csvUtils();
//		System.out.println(envP.getProperty("inputExcelFileName"));
		structureCheck(envP.getProperty("inputExcelFileName"),1);
		finalData = new StringBuffer();
		structureCheck(envP.getProperty("inputExcelTemplate"),2);
		
		rowCSVUtil rowCSVUtil= new rowCSVUtil();
		
//		masterCSVGenrator(envP.getProperty("inputExcelFileName"));
//		masterCSVGenrator(envP.getProperty("inputExcelTemplate"));		
		//Compare Actual and Baseline CSV and Generates a 3rd CSV 
		csvComparison();
//		System.out.println("Actual and Baseline CSV comparison completed.\n");
//		//Converts Back the CSV to Excel
		long endTime   = System.currentTimeMillis();
		System.out.println(totalTime(startTime,endTime));
		valueSetterPropertyFile("summaryRepoValH5",totalTime(startTime,endTime));
		csvtoExcelCOnverion();
		System.out.println("csv to Excel conversion completed.\n");	
	log.info("csv to Excel conversion completed");
		} catch (Exception e){			 
			 throw (e);
			 	 }
		}
	
	public static String totalTime(long startTime,long endTime) {
		long totalTime=endTime-startTime;
		long totalRuntime=totalTime/1000;
		String codeRunTime=String.valueOf(totalRuntime)+" SEC";		
		return codeRunTime;
	}

}
