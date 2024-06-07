package Utililties;

import java.io.File;


import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;



public class ExtentManager {
	
	private static ExtentReports extent;

    public static ExtentReports getInstance(String fileName) {
        if(extent == null) {
            extent = new ExtentReports(fileName, true, DisplayOrder.NEWEST_FIRST);
            extent.loadConfig(new File(System.getProperty("user.dir")+"//reportConfig.xml"));
            extent.addSystemInfo("Rest Assured Version","3.0.2").addSystemInfo("Environment","QA");    
        }
        return extent;
    }

}
