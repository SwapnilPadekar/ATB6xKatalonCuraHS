package com.thetestingacademy.login;

import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Login {

    @Test
    public void loginPosTC() throws Exception {
        WebDriver driver = new EdgeDriver();

        //open the url - https://katalon-demo-cura.herokuapp.com/
        driver.get("https://katalon-demo-cura.herokuapp.com/");
        driver.manage().window().maximize();

        Thread.sleep(3000);

        //click on the make appointment button
        WebElement btn_mkApt = driver.findElement(By.id("btn-make-appointment"));
        btn_mkApt.click();

        //verify that url changes - assert
        String expectedURLafterMkApt = "https://katalon-demo-cura.herokuapp.com/profile.php#login";
        String actualURLafterMkApt = driver.getCurrentUrl();
        Assert.assertEquals(expectedURLafterMkApt,actualURLafterMkApt);


        //time.sleep(3)
        Thread.sleep(3000);

        WebElement tf_username = driver.findElement(By.xpath("//input[@id='txt-username']"));
        tf_username.sendKeys("John Doe");

        WebElement tf_password = driver.findElement(By.xpath("//input[@id='txt-password']"));
        tf_password.sendKeys("ThisIsNotAPassword");

        WebElement btn_Login = driver.findElement(By.xpath("//button[@id='btn-login']"));
        btn_Login.click();

        String expectedURLafterLogin = "https://katalon-demo-cura.herokuapp.com/#appointment";
        String actualURLafterLogin = driver.getCurrentUrl();
        Assert.assertEquals(actualURLafterLogin,expectedURLafterLogin);

        //  Verify that a certain element is displayed
        //WebElement headingText = driver.findElement(By.tagName("h2"));
        WebElement headingText = driver.findElement(By.xpath("//h2[contains(text(),'Make Appointment')]"));
        assertTrue(headingText.isDisplayed(), "Make Appointment");

        WebElement date=driver.findElement(By.id("txt_visit_date"));
        date.sendKeys("12/07/2024");
        WebElement comment=driver.findElement(By.id("txt_comment"));
        comment.sendKeys("Viral fever");
        WebElement bookApt=driver.findElement(By.id("btn-book-appointment"));
        bookApt.click();

        WebElement bookedApt=driver.findElement(By.xpath("//div[@class=\"col-xs-12 text-center\"]/h2"));
        assertThat(bookedApt.getText(), Matchers.equalTo("Appointment Confirmation"));

        Thread.sleep(3000);

        driver.quit();


    }

}
