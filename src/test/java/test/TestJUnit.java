package test;
import io.github.bonigarcia.wdm.WebDriverManager;


import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.lang.Double.parseDouble;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
public class TestJUnit {
    final static Logger logger = Logger.getLogger(TestJUnit.class);
    private static WebDriver _driver = null;
    private static double _price;
    private static double _num;

    @BeforeAll
    public static void SetUp() {
        logger.info("Testing has been started");

        WebDriverManager.chromedriver().browserVersion("94.0.4606.61 ").setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("enable-automation");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-browser-side-navigation");
        options.addArguments("--disable-gpu");
        _driver = new ChromeDriver(options);

    }

    @Test
    @Order(1)
    @DisplayName("First Load Test")
    public void PageOpeningTest() {
        logger.info("First Load Test is running");
        try {
            _driver.get("https://www.lcwaikiki.com/tr-TR/TR");
            WebElement headerLogo = new WebDriverWait(_driver, 5).until(ExpectedConditions.elementToBeClickable(By.className("header-logo")));
            assertTrue(true);
            logger.info("First Load Test was ended with success");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            assertFalse(true);
        }

    }

    @Test
    @Order(2)
    @DisplayName("Login Panel Test")
    public void LoginPanelOpeningTest() {
        logger.info("Login Panel Test is running");
        try {

            WebElement login = _driver.findElement(By.id("header-user-section"));
            login.click();
            WebElement userLogin = new WebDriverWait(_driver, 5).until(ExpectedConditions.elementToBeClickable(By.className("user-login")));
            logger.info("Login Panel Test was ended with success");
            assertTrue(true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            assertFalse(true);
        }
    }

    @Test
    @Order(3)
    @DisplayName("Login Test")
    public void LogInTest() {
        logger.info("Login Test is running");
        try {

            WebElement loginMail = _driver.findElement(By.id("LoginEmail"));
            WebElement loginPassword = _driver.findElement(By.id("Password"));
            WebElement loginLink = _driver.findElement(By.id("loginLink"));
            loginMail.sendKeys("ahtsrh53@gmail.com");
            loginPassword.sendKeys("iATjhvCu4q.f*mc");
            loginLink.click();
            logger.info("Login Test was ended with success");
            assertTrue(true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            assertFalse(true);
        }
    }

    @Test
    @Order(4)
    @DisplayName("Searching Test")
    public void SearchingTest() {
        logger.info("Searching Test is running");
        try {

            WebElement searchInput = _driver.findElement(By.id("search"));
            WebElement searchLink = _driver.findElement(By.className("search-button"));
            searchInput.sendKeys("pantolon");
            searchLink.click();
            logger.info("Searching Test was ended with success");
            assertTrue(true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            assertFalse(true);
        }
    }

    @Test
    @Order(5)
    @DisplayName("Load More Test")
    public void LoadMoreTest() {
        logger.info("Load More Test is running");
        try {
            logger.info("Searching for pantolon");
            _driver.get("https://www.lcwaikiki.com/tr-TR/TR/arama?q=pantolon");
            JavascriptExecutor js = (JavascriptExecutor) _driver;
            js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
            logger.info("Scrolling to end");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            WebElement button = _driver.findElement(By.className("lazy-load-button"));
            button.click();
            logger.info("Load More Test was ended with success");
            assertTrue(true);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            assertFalse(true);
        }

    }

    @Test
    @Order(6)
    @DisplayName("Product Selecting Test")
    public void ProductSelectingTest() {
        logger.info("Product Selecting Test is running");
        try {
            List<WebElement> elements = _driver.findElements(By.className("a_model_item"));
            Random r = new Random();
            WebElement selectedOne = elements.get(r.nextInt(elements.size() - 1));
            selectedOne.click();
            List<WebElement> sizes = _driver.findElements(By.cssSelector("#option-size a"));
            for (WebElement e : sizes
            ) {
                if (e.isEnabled()) {
                    e.click();
                    break;
                }
            }
            logger.info("Product Selecting Test was ended with success");
            assertTrue(true);
        } catch (IndexOutOfBoundsException e) {
            logger.fatal(e.getMessage(), e);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            assertFalse(true);
        }
    }

    @Test
    @Order(7)
    @DisplayName("Adding to Chart Test")
    public void AddingToChartTest() {
        logger.info("Product Selecting Test is running");
        try {
            WebElement addChartButton = _driver.findElement(By.id("pd_add_to_cart"));
            addChartButton.click();

            WebElement e = _driver.findElement(By.className("price"));
            _price = ConvertPrice(e);
            logger.debug("Converted Price : " + _price);
            logger.info("Product Selecting Test was ended with success");
            assertTrue(true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            assertFalse(true);
        }
    }

    @Test
    @Order(8)
    @DisplayName("Increment Test")
    public void IncrementTest() {
        logger.info("Increment Test is running");
        try {
            WebElement chartButton = _driver.findElement(By.className("header-cart"));
            chartButton.click();
            WebElement e = _driver.findElement(By.className("option-quantity"));
            WebElement addElement = e.findElement(By.className("plus"));
            addElement.click();

            WebElement ele = new WebDriverWait(_driver, 5).until(ExpectedConditions.elementToBeClickable(By.className("alert-block")));
            WebElement numElement = _driver.findElement(By.className("item-quantity-input"));
            _num = parseDouble(numElement.getAttribute("value"));
            logger.debug("Number of Product : " + _num);
            logger.info("Increment Test was ended with success");
            assertTrue(true);
        } catch (NumberFormatException e) {
            logger.error(e.getMessage(), e);
            assertFalse(true);
        }
    }

    @Test
    @Order(9)
    @DisplayName("Summary Checking Test")
    public void SummaryCheckingTest() {
        logger.info("Summary Checking Test is running");
        try {
            WebElement countElement = _driver.findElement(By.className("rd-cart-item-price"));

            double count = ConvertPrice(countElement);
            double expected = _price*_num;
            assertEquals(expected, count);
            logger.debug("Expected : " +expected);
            logger.debug("Actual : " +count);
            logger.info("Summary Checking Test was ended with success");
        } catch (NumberFormatException e) {
            logger.error(e.getMessage(), e);
            assertFalse(true);
        }

    }

    @Test
    @Order(10)
    @DisplayName("Product Deleting Test")
    public void ProductDeletingTest() {
        logger.info("Product Deleting Test is running");
        try {
            WebElement deleteButton = _driver.findElement(By.className("cart-square-link"));
            deleteButton.click();

            WebElement delete = new WebDriverWait(_driver, 5).until(ExpectedConditions.elementToBeClickable(By.className("sc-delete")));
            delete.click();


            WebElement emptyText = new WebDriverWait(_driver, 5).until(ExpectedConditions.elementToBeClickable(By.className("cart-empty-title")));
            logger.info("Product Deleting Test was ended with success");
            assertTrue(true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            assertFalse(true);
        }

    }

    private double ConvertPrice(WebElement e) {
        logger.debug("Convert Price method was started");
        String price = e.getAttribute("innerHTML");
        logger.debug("Price of Product : " + price);
        int l = price.indexOf("TL", 0);
        return parseDouble(price.substring(0, l - 1).replace(",", "."));
    }
}
