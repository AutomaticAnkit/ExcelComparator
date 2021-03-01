package SelniumPractice.WebAutomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExcelcsvComp extends csvUtils{
	
	//This class is used for Comparing PROD and UAT Excel and produce the results.
	
	public ExcelcsvComp() throws IOException {		
		super();		 
	}
	static long startTime = System.currentTimeMillis();
		
	public static void main(String[] args) throws Exception  {
		System.out.println("Comparison between "+getValFromEnvPropFile("inputExcelFileName")+".xlsx"+" & "+ getValFromEnvPropFile("inputExcelTemplate")+".xlsx has been iniated.");
//		Structure Check Methods checks the Structure of the table.
		structureCheck(getValFromEnvPropFile("inputExcelFileName"),1);
		finalData = new StringBuffer();
		structureCheck(getValFromEnvPropFile("inputExcelTemplate"),2);		
		//Compare Actual and Baseline CSV and Generates a 3rd CSV 
		csvComparison();
		//Converts Back the CSV to Excel
		long endTime   = System.currentTimeMillis();
		//Values Updated in ENV Property file for Summary Report
		valueSetterPropertyFile("summaryRepoValH5",totalTime(startTime,endTime));
		//Final Excel Creation.
		csvtoExcelCOnverion();
		System.out.println("Comparison has been completed.\n The File is placed on the below path \n"+getValFromEnvPropFile("finalExcelFolderPath"));		
		}
	
	//totalTime method returns the total time took to run the code in seconds.
	public static String totalTime(long startTime,long endTime) {
		long totalTime=endTime-startTime;
		long totalRuntime=totalTime/1000;
		String codeRunTime=String.valueOf(totalRuntime)+" SEC";		
		return codeRunTime;
	}

}
