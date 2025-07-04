package Utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static HashMap<String, ExtentReports> reportMap = new HashMap<>();
    private static final String REPORT_BASE_DIR = "target/ExtentReports/";

    public static ExtentReports getReporter(String featureName) {
        if (reportMap.containsKey(featureName)) {
            return reportMap.get(featureName);
        }

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportPath = REPORT_BASE_DIR + featureName + "/ExtentReport_" + timestamp + ".PDF";

        File reportFile = new File(reportPath);
        reportFile.getParentFile().mkdirs();

        ExtentSparkReporter reporter = new ExtentSparkReporter(reportFile);
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Feature", featureName);
        extent.setSystemInfo("Author", "Automation Team");

        reportMap.put(featureName, extent);
        return extent;
    }
}
