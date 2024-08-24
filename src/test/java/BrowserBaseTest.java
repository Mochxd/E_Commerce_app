import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BrowserBaseTest {
    protected AndroidDriver androidDriver;
    protected AppiumDriverLocalService service;
    @BeforeMethod
    public void configureAppium() throws MalformedURLException {
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\zas\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
        service.start();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Mostafa");
        options.setChromedriverExecutable("D:\\Appium\\Raul\\src\\main\\resources\\chromedriver.exe");
        options.setCapability("browserName","Chrome");
        androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/"),options);
        androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
}
