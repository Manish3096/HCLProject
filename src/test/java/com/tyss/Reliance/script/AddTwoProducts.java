package com.tyss.Reliance.script;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tyss.Bluestone1.generics.Filelib;

public class AddTwoProducts {
	static 
	{
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	}
	public static void main(String[] args) throws IOException, InvalidFormatException, InterruptedException {
       
		
		Filelib f = new Filelib();
		/*Fetch 2nd row 1st column*/
		String tabname = f.getExcelData("RelianceDigi", 2, 0);
		
		/*Fetch 2nd row 2nd column*/
		String mobileandtabdd = f.getExcelData("RelianceDigi", 2, 1);
		
		/*Fetch 2nd row 3rd column*/
		String pin = f.getExcelData("RelianceDigi", 2, 2);
		
		String pname = f.getExcelData("RelianceDigi", 2, 4);
		
		String pname1 = f.getExcelData("RelianceDigi", 3, 4);
		
		/*Customised Option to use Chrome*/
		ChromeOptions options = new ChromeOptions();
		 
		options.addArguments("--disable-notifications");
		 
		 WebDriver d = new ChromeDriver(options);
		
		 /*Maximize window*/
		d.manage().window().maximize();
		
		/*Wait condition*/
		d.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		/*Navigate to the url*/
		d.get("https://www.reliancedigital.in/");
		
		/*Select a Tab*/
		WebElement mobile = d.findElement(By.xpath("//div[@class='nav__title' and text()='"+tabname+"']"));
		Actions a = new Actions(d);
		a.moveToElement(mobile).perform();
		
		/*Select an option from Mobile and Tablets dropdown*/
		d.findElement(By.xpath("//a[text()='"+mobileandtabdd+"' and @class='nav__description__hoverULine']")).click();

		JavascriptExecutor js = (JavascriptExecutor) d;
		js.executeScript("window.scrollBy(0,400)", "");
		/*Select Mi Mobile*/
		d.findElement(By.xpath("(//img[@alt='Logo_Mi.jpg'])[2]")).click();
		
		/*Select a Mi product*/
		WebElement mi1 = d.findElement(By.xpath("//p[contains(text(),'"+pname+"')]"));
		a.moveToElement(mi1).perform();
		a.click(mi1).perform();
		
	     Set<String> allwh = d.getWindowHandles();
	     Iterator<String> i = allwh.iterator();
	     String pid = i.next();
	     String cid = i.next();
	     String cid1 = i.next();
	     
	    d.switchTo().window(cid1);
		/*Enter the Pin*/
		d.findElement(By.id("RIL_PDPInputPincode")).sendKeys(pin);
		
		/*Click on Add to Cart Button*/
		Thread.sleep(3000);
		WebElement addto = d.findElement(By.xpath("//button[@id='add_to_cart_main_btn']"));
		a.moveToElement(addto).click().perform();
		/*Click on Skip and Continue*/
		WebDriverWait wait2 = new WebDriverWait(d,30);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='btn-continue-to-cart']")));
		
		d.findElement(By.xpath("//button[@id='btn-continue-to-cart']")).click();

		d.close();
		
		
		Thread.sleep(3000);
		d.switchTo().window(pid);
		
		
		
		/*Select a Tab*/
		WebElement mobile1 = d.findElement(By.xpath("//div[@class='nav__title' and text()='"+tabname+"']"));
		
		a.moveToElement(mobile1).perform();
		
		/*Select an option from Mobile and Tablets dropdown*/
		d.findElement(By.xpath("//a[text()='"+mobileandtabdd+"' and @class='nav__description__hoverULine']")).click();

		//JavascriptExecutor js = (JavascriptExecutor) d;
		js.executeScript("window.scrollBy(0,400)", "");
		/*Select Mi Mobile*/
		d.findElement(By.xpath("(//img[@alt='Logo_Mi.jpg'])[2]")).click();
		
		/*Select a Mi product*/
		WebElement mi2 = d.findElement(By.xpath("//p[contains(text(),'"+pname1+"')]"));
		a.moveToElement(mi2).perform();
		a.click(mi2).perform();
		
	     Set<String> allwh1 = d.getWindowHandles();
	     Iterator<String> i1 = allwh1.iterator();
	     String pid1 = i1.next();
	     String cid2 = i1.next();
	     String cid3 = i1.next();
	     
	    d.switchTo().window(cid3);
		/*Enter the Pin*/
		//d.findElement(By.id("RIL_PDPInputPincode")).sendKeys(pin);
		
		/*Click on Add to Cart Button*/
	    WebDriverWait wait4 = new WebDriverWait(d,30);
		wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='add_to_cart_main_btn']")));
		
		WebElement addto1 = d.findElement(By.xpath("//button[@id='add_to_cart_main_btn']"));
		a.moveToElement(addto1).click().perform();
		/*Click on Skip and Continue*/
		WebDriverWait wait3 = new WebDriverWait(d,30);
		wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='btn-continue-to-cart']")));
		
		d.findElement(By.xpath("//button[@id='btn-continue-to-cart']")).click();

		
		
		WebElement noofitems = d.findElement(By.xpath("//h3[text()='My Cart']/../h3//span[@class='cpl__title__count']"));
        System.out.println(noofitems.getText());   
	}
}