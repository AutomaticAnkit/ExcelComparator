package ExcelComparator.Runner;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ExcelComparator.Tests.csvUtils;
import ExcelComparator.Utils.GeneralUtils;

public class ExcelcsvComp extends csvUtils{
	private static Logger log = LogManager.getLogger(ExcelcsvComp.class.getName());
	
	//This class is used for Comparing PROD and UAT Excel and produce the results.
	
	public ExcelcsvComp() throws IOException {		
		super();		 
	}
	static long startTime = System.currentTimeMillis();
		
	public static void main(String[] args) throws Exception  {
		//log.info("Comparison between "+getValFromEnvPropFile("inputExcelFileName")+".xlsx"+" & "+ getValFromEnvPropFile("inputExcelTemplate")+".xlsx has been iniated.");
		log.info("Comparison between " + GeneralUtils.getEnvironment("inputExcelFileName")+".xlsx"+" & "+ GeneralUtils.getEnvironment("inputExcelTemplate")+".xlsx has been iniated.");
		//System.out.println("Comparison between "+getValFromEnvPropFile("inputExcelFileName")+".xlsx"+" & "+ getValFromEnvPropFile("inputExcelTemplate")+".xlsx has been iniated.");
		System.out.println("Comparison between "+ GeneralUtils.getEnvironment("inputExcelFileName")+".xlsx"+" & "+ GeneralUtils.getEnvironment("inputExcelTemplate")+".xlsx has been iniated.");
		//		Structure Check Methods checks the Structure of the table.
		structureCheck(GeneralUtils.getEnvironment("inputExcelFileName"),1);
		log.info("Validated the Structure of the table for "+GeneralUtils.getEnvironment("inputExcelFileName") );
		finalData = new StringBuffer();
		structureCheck(GeneralUtils.getEnvironment("inputExcelTemplate"),2);		
		log.info("Validated the Structure of table for "+GeneralUtils.getEnvironment("inputExcelTemplate") );
		//Compare Actual and Baseline CSV and Generates a 3rd CSV 
		csvComparison(missingValuesMap);
		log.info("Compared the ACtual and Baseline CSV and Generates 3rd CSV");
		//Converts Back the CSV to Excel
		long endTime   = System.currentTimeMillis();
		//Values Updated in ENV Property file for Summary Report
		valueSetterPropertyFile("summaryRepoValH5",totalTime(startTime,endTime));
		log.info("Values updated in the ENV file");
		//Final Excel Creation.
		csvtoExcelCOnverion();
		log.info("Converted CSV into Excel");
		System.out.println("Comparison has been completed.\n The File is placed on the below path \n"+GeneralUtils.getEnvironment("finalExcelFolderPath"));		
	log.info("Comparison has been completed");	
	}
	
	//totalTime method returns the total time took to run the code in seconds.
	public static String totalTime(long startTime,long endTime) {
		long totalTime=endTime-startTime;
		long totalRuntime=totalTime/1000;
		String codeRunTime=String.valueOf(totalRuntime)+" SEC";		
		return codeRunTime;
	}

}
