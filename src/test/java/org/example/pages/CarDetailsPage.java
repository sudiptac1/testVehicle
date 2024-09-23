package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CarDetailsPage {

    public WebElement getInputElement(WebDriver driver) {

        WebElement inputElement = driver.findElement(By.id("registration-number-input"));
        return inputElement;
    }

    public WebElement getFindButton(WebDriver driver) {

        WebElement findElement = driver.findElement(By.id("find-vehicle-btn"));
        return findElement;
    }

    public WebElement getRegNoElement(WebDriver driver) {

        WebElement regNoElement = driver.findElement(By.xpath("/html[1]/body[1]/main[1]/form[1]/div[2]/div[1]/div[2]/div[1]/div[1]/p[1]/b[1]"));
        return regNoElement;
    }


    public WebElement getMakeElement(WebDriver driver) {

        WebElement makeElement = driver.findElement(By.xpath("//html[1]/body[1]/main[1]/form[1]/div[2]/div[1]/div[2]/div[1]/div[1]/p[2]/b[1]"));
        return makeElement;
    }
    
    public WebElement changeVehicleElement(WebDriver driver) {
        WebElement changeVehElement = driver.findElement(By.id("change-vehicle-btn"));
        return changeVehElement;
    }

    public WebElement getYearElement(WebDriver driver) {
        WebElement yearElement = driver.findElement(By.xpath("/html[1]/body[1]/main[1]/form[1]/div[2]/div[1]/div[2]/div[1]/div[1]/p[5]/b[1]"));
        return yearElement;
    }

}

