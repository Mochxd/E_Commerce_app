import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    protected AndroidDriver androidDriver;
    protected AppiumDriverLocalService service;
//    @BeforeMethod
//    public void preSetup(){
//        Activity activity = new Activity("io.appium.android.apis","io.appium.android.apis.preference.PreferenceDependencies");
//        ((JavascriptExecutor) androidDriver).executeScript("mobile: startActivity",
//                ImmutableMap.of("intent","io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies"));
//    }
    @BeforeMethod
    public void configureAppium() throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/properties.properties");
        properties.load(fis);

        String ipAddress = properties.getProperty("ipAddress");
        String port = properties.getProperty("port");
        String deviceName = properties.getProperty("AndroidDeviceName");

        service = startAppiumServer(ipAddress, Integer.parseInt(port));

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(deviceName);
        options.setApp("D:\\Appium\\E_Commerce_app\\src\\main\\resources\\General-Store.apk");
        options.setChromedriverExecutable("D:\\Appium\\E_Commerce_app\\src\\main\\resources\\chromedriver.exe");
        androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/"),options);
        androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public List<HashMap<String, String>> getJsonData(String jsonPathFile) throws IOException {

        // Read the content of the file specified by the jsonPathFile
        String jsonContent = FileUtils.readFileToString(new File(jsonPathFile), "UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        // Convert the JSON content to a List of HashMap objects, where each HashMap represents a JSON object
        List<HashMap<String, String>> data = mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {});
        return data;
    }
    public AppiumDriverLocalService startAppiumServer(String ipAddress, int Port) throws MalformedURLException {
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\zas\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress(ipAddress)
                .usingPort(Port)
                .build();
        service.start();
        return service;
    }

    @AfterMethod
    public void tearDown(){
        androidDriver.quit();
        service.stop();
    }
}