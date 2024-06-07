package baseClass;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import LoggerImplement.Log4j;
import Utililties.ExtentManager;
import Utililties.ReadingPropertiesFile;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class BasePage {
	
	public static Logger log = Log4j.getLogger(Log4j.class);
	 public static String id = "";

	    protected static ReadingPropertiesFile readingPropertiesFile =new ReadingPropertiesFile();
	    public static ExtentReports extent;
	    public static com.relevantcodes.extentreports.ExtentTest test;
	    protected static RequestSpecification httpRequest;
	    
	    @BeforeSuite
	    public static void init() {
	        extent = ExtentManager.getInstance("reports/ExtentReports.html");
	    }
	    
	    @BeforeMethod
	    public void startTest(Method method) {
	        httpRequest = RestAssured.given();

	        
	        test = extent.startTest(method.getName()); 
	    }
	    
	    @AfterMethod
	    public void reportFlush(ITestResult result) {

	        System.out.println(result.getMethod().getMethodName());
	        if (result.getStatus() == ITestResult.FAILURE)
	            test.log(LogStatus.FAIL, result.getThrowable());
	        else if (result.getStatus() == ITestResult.SKIP) 
	            test.log(LogStatus.SKIP, result.getThrowable());
	        else
	            test.log(LogStatus.PASS, "Test Passed");
	        extent.flush();
	    }

}
