package BaseApi;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/*
* Created with IntelliJ IDEA.
* User: rrt
* Date: 12/13/14
* Time: 1:34 PM
* To change this template use File | Settings | File Templates.
*/
public class CommonApi {
    public WebDriver driver = null;
    @Parameters({"browserName","url"})
    @BeforeMethod
    public void beforeMethod(@Optional("chrome")String browser, @Optional("http://www.cnn.com")String url) {

        driver = getLocalDriver(browser);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to(url);
        driver.manage().window().maximize();
    }
    public WebDriver getLocalDriver(String browser){
        if(browser.equalsIgnoreCase("firfox")){
            driver = new FirefoxDriver();
        }else if(browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", "Generic/drivers/chromedriver");
            driver = new ChromeDriver();
        }else if(browser.equalsIgnoreCase("safari")){
            driver = new SafariDriver();
        }else if(browser.equalsIgnoreCase("iexplore")){
            driver = new InternetExplorerDriver();
        }
        return driver;
    }

    @AfterMethod
    public void afterMethod() {
        driver.close();
    }

    //Utility methods

    public List<WebElement> getListOfWebElement(String locator){
        List<WebElement> list = driver.findElements(By.cssSelector(locator));

        return list;
    }

    public List<String> getListOfText(List<WebElement> list){
        List<String> text = new ArrayList<String>();
      for(WebElement element:list){
          text.add(element.getText());
      }

      return text;
    }

    //click
    public void navigateBack(){
        driver.navigate().back();
    }

    public void clickOnCss(String locator){
        driver.findElement(By.cssSelector(locator)).click();
    }
    public void clickOnXpath(String locator){
        driver.findElement(By.xpath(locator)).click();
    }
    public void clickOnId(String locator){
        driver.findElement(By.id(locator)).click();
    }
    public void clickOnName(String locator){
        driver.findElement(By.name(locator)).click();
    }

    //type
    public void typeByCss(String locator, String value){
        driver.findElement(By.cssSelector(locator)).sendKeys(value, Keys.ENTER);

    }
    public void typeByXpath(String locator, String value){
        driver.findElement(By.xpath(locator)).sendKeys(value);

    }

    //Alert handling
    public void okAlert(String locator){
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    public void cancelAlert(String locator){
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public void sleep(int seconds)throws InterruptedException{
        Thread.sleep(1000*seconds);
    }

    //Syncronization
    public void waitUntilVisible(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }
    public void waitUntilClickable(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

    }
    public void waitUntilSelectable(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        boolean element = wait.until(ExpectedConditions.elementToBeSelected(locator));

    }
    //select menu item

    public void selectElementByOption(WebElement element, String value){
        Select select = new Select(element);
        select.selectByVisibleText(value);
    }






}