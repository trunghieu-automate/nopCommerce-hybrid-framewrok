package actions.common;

import java.io.File;

public class Constants {
    // path
    public static final String sysPath = System.getProperty("user.dir");
    public static final String mainResourcePath = sysPath + File.separator+ "src" +File.separator + "main" +File.separator +"resources" + File.separator;

    // URL
    public static final String homePageUrl = "https://demo.nopcommerce.com/";
    public static final String adminPageLogin = "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F";

    // timeout
    public static final long LONG_TIME_OUT = 20;
    public static final long SHORT_TIME_OUT = 10;
}
