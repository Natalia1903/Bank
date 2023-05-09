package pages.customerLogin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageBase;
import wait.Wait;

public class CustomerLoginPage extends PageBase {
    public String userName;

    public CustomerLoginPage(WebDriver driver) {
        super(driver);
    }

    Wait wait;

    @FindBy(id = "userSelect")
    public WebElement userSelect;

    @FindBy(xpath = "//*[@type='submit']")
    public WebElement loginButton;

    public void waitForLoading() {
        wait = new Wait(driver);
        wait.forVisibility(userSelect);
    }

    public void selectExistingUser(String userName) {
        selectOption(userName, userSelect);
    }

    public void clickOnLoginButton() {
        click(loginButton);
    }

    public void checkForVisibilityLoginButton() {
        wait = new Wait(driver);
        wait.forVisibility(loginButton);
    }
}
