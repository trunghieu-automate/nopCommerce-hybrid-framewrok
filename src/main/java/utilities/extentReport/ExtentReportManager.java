package utilities.extentReport;
import actions.common.Constants;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.Platform;
import org.testng.Reporter;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class ExtentReportManager {
    private static Platform platform;
    private static String reportFileName = "extent-report.html";
    private static String macPath = Constants.sysPath + "/ExtentReportV5";
    private static String windowsPath = Constants.sysPath + "\\ExtentReportV5";
    private static String macReportFileLoc = macPath + "/" + reportFileName;
    private static String winReportFileLoc = windowsPath + "\\" + reportFileName;

    public static ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter;
        switch(getCurrentPlatform()){
            case MAC:
                reporter = new ExtentSparkReporter(macReportFileLoc);
                break;
            case WINDOWS:
                reporter = new ExtentSparkReporter(winReportFileLoc);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + getCurrentPlatform());
        }
        reporter.config().setReportName("Lighting board Testing");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        try {
            extentReports.setSystemInfo("Computer name", InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        extentReports.setSystemInfo("Project", "Nop commercer");
        extentReports.setSystemInfo("Author", "trunghieu-automate");
        extentReports.addTestRunnerOutput(Reporter.getOutput());
        return extentReports;
    }

    // Get current platform
    private static Platform getCurrentPlatform() {
        if (platform == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                platform = Platform.WINDOWS;
            } else if (operSys.contains("nix") || operSys.contains("nux") || operSys.contains("aix")) {
                platform = Platform.LINUX;
            } else if (operSys.contains("mac")) {
                platform = Platform.MAC;
            }
        }
        return platform;
    }
}
