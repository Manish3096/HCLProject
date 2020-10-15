package com.tyss.Reliance.script;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.tyss.Bluestone1.generics.Filelib;

public class VerifyAddress extends Filelib {
	static 
	{
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	}
	public static void main(String[] args) throws IOException, InvalidFormatException {
       
		Filelib f = new Filelib();
		String tabname = f.getExcelData("RelianceDigi", 1, 0);
		String mobileandtabdd = f.getExcelData("RelianceDigi",4, 1);
		
		String storecap = f.getExcelData("RelianceDigi", 4, 2);
		String filter = f.getExcelData("RelianceDigi", 1, 3);
		/*Customised Option to use Chrome*/
		ChromeOptions options = new ChromeOptions();
		 
		options.addArguments("--disable-notifications");
		 
		 WebDriver d = new ChromeDriver(options);
		
		 /*Maximize window*/
		d.manage().window().maximize();
		
		/*Wait condition*/
		d.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		/*Navigate to the url*/
		d.get("https://www.reliancedigital.in/");
		
		/*Select a Tab*/
		WebElement mobile = d.findElement(By.xpath("//div[@class='nav__title' and text()='"+tabname+"']"));
		Actions a = new Actions(d);
		a.moveToElement(mobile).perform();
		
		/*Select an optiion from Mobile and Tablets dropdown*/
		d.findElement(By.xpath("//a[text()='"+mobileandtabdd+"' and @class='nav__description__hoverULine']")).click();
		
		
		WebDriverWait wait = new WebDriverWait(d,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='sp__name']")));
		d.findElement(By.xpath("//p[@class='sp__name']")).click();
        
         WebElement pincode = d.findElement(By.id("RIL_PDPInputPincode"));
         
         pincode.sendKeys(storecap);
         WebElement errormsg = d.findElement(By.xpath("//div[@class='pb__5']"));
         
         if(storecap==null)
         {
        	 System.out.println(errormsg.getText());
         }
         
	}
}
