package Utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.java.Scenario;
import stepdefinition.hooks;

public class ExtentReportManager {

    private static HashMap<String, ExtentReports> reportMap = new HashMap<>();
    private static final String REPORT_BASE_DIR = "target/ExtentReports/";

    public static ExtentReports getReporter(String featureName) {
        if (reportMap.containsKey(featureName)) {
            return reportMap.get(featureName);
        }

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportPath = REPORT_BASE_DIR + featureName + "/ExtentReport_" + timestamp + ".html";

        File reportFile = new File(reportPath);
        reportFile.getParentFile().mkdirs();

        ExtentSparkReporter reporter = new ExtentSparkReporter(reportFile);
        ExtentReports extent = new ExtentReports();
        //This tells extent how and where to generate the report.
        extent.attachReporter(reporter);
        //System/Environment inside the reports
        extent.setSystemInfo("Feature", featureName);
        extent.setSystemInfo("Author", "Automation Team");

        reportMap.put(featureName, extent);
        return extent;
    }

  //Extent reports
    public static String getFeatureName() {
        String raw =hooks. scenario.getUri().toString(); // For Cucumber 7+//here by using scenario interface existing methods we 
                                                  //can get the current running feature path.
        return new File(raw).getName().replace(".feature", "");//receive the feature name without feature extension.
    }
  


}
