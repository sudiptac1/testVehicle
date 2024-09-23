package org.example;

import org.example.pages.CarDetailsPage;
import org.example.utils.CarRegDetails;
import org.example.utils.OutFileReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
//import org.junit.Assert;


public class TestcasesVehicle {

    static WebDriver driver;

    @BeforeEach
    public void setUp() throws Exception {

        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.get("https://www.confused.com/");

        WebElement acceptElement = driver.findElement(By.id("button-save-all"));
        acceptElement.click();

        WebElement carQuoteElement = driver.findElement(By.linkText("Get a car quote"));
        carQuoteElement.click();

    }

    @ParameterizedTest
    @MethodSource("org.example.utils.InputFileReader#getCarReg")
    public void checkVehicleDtls(String argCarReg) {

        CarDetailsPage carDetailspage = new CarDetailsPage();
        WebElement inputElement = carDetailspage.getInputElement(driver);
        WebElement buttonElement = carDetailspage.getFindButton(driver);
        inputElement.sendKeys(argCarReg);
        buttonElement.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        List errorElements = driver.findElements(By.id("#registration-correct-link"));

        if (errorElements.size() > 0) {
            Assertions.fail("test failed");
        }

        List vehicleDtlsElements = driver.findElements(By.id("vehicleSummaryRegNumber"));
        if (vehicleDtlsElements.size() > 0) {
            WebElement testElement = driver.findElement(By.cssSelector("p#vehicleSummaryRegNumber b"));
            WebElement regElement = carDetailspage.getRegNoElement(driver);
            CarRegDetails car = OutFileReader.getCarDetails().get(argCarReg.replace(" ", ""));
            Assertions.assertEquals(regElement.getText().replace(" ", ""), car.getRegNo());
            WebElement makeElement = carDetailspage.getMakeElement(driver);
            Assertions.assertEquals(makeElement.getText(), car.getMake());
            WebElement yearElement = carDetailspage.getYearElement(driver);
            Assertions.assertEquals(yearElement.getText(), car.getYear());
        } else
            Assertions.fail("Vehicle not found");
    }

    @AfterEach
    public void tearDown() throws Exception {
        driver.quit();
        driver = null;

    }
}
