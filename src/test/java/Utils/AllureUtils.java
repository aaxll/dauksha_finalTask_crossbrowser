package Utils;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AllureUtils {
    public void setAllureEnvironmentProp() {
        if (WebDriverRunner.getWebDriver() instanceof HasCapabilities) {
            Capabilities capabilities = ((HasCapabilities) WebDriverRunner.getWebDriver()).getCapabilities();
            String browserName = capabilities.getBrowserName();
            String browserVersion = capabilities.getVersion();
            String platform = String.valueOf(capabilities.getPlatform());
            String selenideVersion = SelenideDriver.class.getPackage().getImplementationVersion();

            try{
                File file = new File("allure-results/environment.properties");
                FileWriter newFile = new FileWriter(file, false);
                PropertiesConfiguration conf = new PropertiesConfiguration(file);
                conf.setProperty("Browser name: ", browserName);
                conf.setProperty("Browser version: ", browserVersion);
                conf.setProperty("Platform: ", platform);
                conf.setProperty("Selenide version: ", selenideVersion);
                conf.save();
            }
            catch (ConfigurationException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }


        }
    }
}
