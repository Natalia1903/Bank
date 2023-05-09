package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import tests.ApplicationManager;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Arrays;


public class PageBase {
    public WebDriver driver;


    public PageBase(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element) {
        element.click();
    }

    public void selectOption(String optionName, WebElement selectElement) {
        Select select = new Select(selectElement);
        select.selectByVisibleText(optionName);
    }

    public void fillField(WebElement field, String text) {
        click(field);
        field.clear();
        field.sendKeys(text);
    }


    public static class TestBase {
        static Logger logger = LoggerFactory.getLogger(TestBase.class);
        protected static ApplicationManager app = new ApplicationManager();

        @BeforeMethod
        public void setupTest() throws MalformedURLException {
            app.init(false);
        }

        @BeforeMethod
        public void startTest(Method m, Object[] p) {
            logger.info("Start test " + m.getName() + " with data: " + Arrays.asList(p));
        }

        @AfterMethod
        public void tearDown() {
            app.stop();
        }

        @AfterMethod
        public void stopTest(ITestResult result) {
            if (result.isSuccess()) {
                logger.info("PASSED" + result.getMethod().getMethodName());
            } else {
                logger.info("FAILED" + result.getMethod().getMethodName());
            }

            logger.info("=========================================================================");
        }
    }
}
