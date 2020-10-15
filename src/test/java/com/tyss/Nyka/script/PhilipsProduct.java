package com.tyss.Nyka.script;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
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

import com.tyss.Bluestone1.generics.Filelib;

public class PhilipsProduct {
	static 
	{
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	}
	public static void main(String[] args) throws IOException, InvalidFormatException, InterruptedException {


		Filelib f = new Filelib();

		/*Customised Option to use Chrome*/
		ChromeOptions options = new ChromeOptions();

		options.addArguments("--disable-notifications");

		WebDriver d = new ChromeDriver(options);

        WebDriverWait wb = new WebDriverWait(d, 50);

		/*Maximize window*/
		d.manage().window().maximize();

		/*Wait condition*/
		d.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
         
		/*Navigate to the url*/
		d.get("https://www.nykaa.com/");
        Actions a = new Actions(d);
        d.findElement(By.xpath("//span[text()='Account']")).click();
        d.findElement(By.xpath("//span[@class='new-login-button new-login-button--google']")).click();
        //d.findElement(By.xpath("//div[text()='Use another account']")).click();
        
        
        String emailidi = f.getExcelData("Nykaa1", 1, 11);
        d.findElement(By.id("identifierId")).sendKeys(emailidi);
        
        d.findElement(By.id("identifierNext")).click();
        //d.findElement(By.id("next")).click();
        
        String password = f.getExcelData("Nykaa1", 1, 12);
        d.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        
        d.findElement(By.id("passwordNext")).click();
        //d.findElement(By.id("next")).click();
        
        
        WebElement appliancetab = d.findElement(By.xpath("//li[@class='MegaDropdownHeadingbox']/../li[4]"));
		a.moveToElement(appliancetab).perform();

		String appliancecategory= f.getExcelData("Nykaa1", 1, 8);
		WebElement applicationdd = d.findElement(By.xpath("(//a[text()='"+appliancecategory+"'])[2]"));
		a.moveToElement(applicationdd).click().perform();
		Set<String> allwh1 = d.getWindowHandles();
		Iterator<String> i1 = allwh1.iterator();
		String pid1 = i1.next();
		String cid2 = i1.next();
		String cid3 = i1.next();

		d.switchTo().window(cid3);

		
		d.findElement(By.xpath("//ul[@class='pagination-box']//following-sibling::li[3]/a/span[text()='3']")).click();
	
	    WebElement product = d.findElement(By.xpath("//div[@class='card-img']/../div[1]/div[1]/img"));
	    a.moveToElement(product).perform();
	    
	    d.findElement(By.xpath("(//span[@class='wishListIconWrapper'])[1]")).click();
	    
	    WebElement myaccount = d.findElement(By.xpath("//span[@class='AccountText']"));
	    a.moveToElement(myaccount).perform();
	    
	    WebElement wishlist = d.findElement(By.xpath("//a[text()='My Wishlist']"));
	   a.moveToElement(wishlist).click().perform();
	    d.findElement(By.xpath("(//span[text()='Notify Me'])[1]")).click();
	    
	    String emailid = f.getExcelData("Nykaa1", 1, 11);
	    d.findElement(By.xpath("(//input[@name='email'])[2]")).sendKeys(emailid);
	    
	    d.findElement(By.xpath("(//span[text()='Notify Me'])[2]")).click();
	}
}