package org.example;

import org.example.pages.CarDetailsPage;
import org.example.utils.CarRegDetails;
import org.example.utils.InputFileReader;
import org.example.utils.OutFileReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import org.junit.Assert;




public class junit5TestcasesVehicle {

   static  WebDriver driver;


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

        System.out.println("start of the test"+ argCarReg);

        inputElement.sendKeys(argCarReg);

        buttonElement.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));


        List errorElements =driver.findElements(By.id("#registration-correct-link"));
        if(errorElements.size()>0){
            System.out.println("In error value");
            Assert.fail("test failed");
        }

        List vehicleDtlsElements =driver.findElements(By.id("vehicleSummaryRegNumber"));
        if(vehicleDtlsElements.size()>0){
            WebElement testElement = driver.findElement(By.cssSelector("p#vehicleSummaryRegNumber b"));

            System.out.println("testElement.getText()");
            System.out.println(testElement.getText());


            WebElement regElement = carDetailspage.getRegNoElement(driver);

            System.out.println("regElement");
            System.out.println(regElement.getText());

            System.out.println("carobjects" + OutFileReader.getCarDetails().get(argCarReg.replace(" " , "")));
            CarRegDetails car = OutFileReader.getCarDetails().get(argCarReg.replace(" " , ""));

            Assert.assertEquals(regElement.getText().replace(" " , ""),car.getRegNo());
            System.out.println("reg matching");

            WebElement makeElement = carDetailspage.getMakeElement(driver);
            Assert.assertEquals(makeElement.getText(),car.getMake());
            System.out.println("make matching");

            WebElement yearElement = carDetailspage.getYearElement(driver);
            Assert.assertEquals(yearElement.getText(),car.getYear());
            System.out.println("year matching");

        }
        else
           Assert.fail("Vehicle not found");

    }

    @AfterEach
    public void tearDown() throws Exception {
        driver.quit();
        driver = null;

    }
}
