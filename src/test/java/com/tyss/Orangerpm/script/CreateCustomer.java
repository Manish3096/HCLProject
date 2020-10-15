package com.tyss.Orangerpm.script;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.tyss.Bluestone1.generics.Filelib;

public class CreateCustomer extends Filelib{
	static
	{
	        System.setProperty("webdriver.chrome.driver", "./dr/chromedriver.exe");
	}
	@Test
	
	public void CreateCustomer() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		Filelib f = new Filelib();
		
		/*fetch data from 1st row 1st column*/
		String userid = f.getExcelData("Orangehrm", 1, 0);
		
		/*fetch data from 1st row 2nd column*/
		String password = f.getExcelData("Orangehrm", 1, 1);
		
		/*fetch data from 1st row 3rd column*/
		String project = f.getExcelData("Orangehrm", 1, 2);
		
		/*fetch data from 1st row 4th column*/
		String custom = f.getExcelData("Orangehrm", 1, 3);
		
		/*fetch data from 1st row 5th column*/
		String custname = f.getExcelData("Orangehrm", 1, 4);
		
		/*fetch data from 1st row 6th column*/
		String custdes = f.getExcelData("Orangehrm", 1, 5);
		
		/*fetch data from 1st row 7th column*/
		String msg = f.getExcelData("Orangehrm", 1, 6);
		
		/*Launch chromebrowser*/
		WebDriver driver = new ChromeDriver();
		
		/*Manage timeouts*/
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		/*Maximize browser*/
		driver.manage().window().maximize();
		
		/* Openrpm url*/
		driver.get("https://s2.demo.opensourcecms.com/orangehrm/symfony/web/index.php/auth/login");
		
		/*Enter username*/
		driver.findElement(By.id("txtUsername")).sendKeys(userid);
		
		/*Enter password*/
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		
		
		/*click on login button*/
		driver.findElement(By.id("btnLogin")).click();
		
		/*Mousehover to Admin tab*/
		WebElement admintab = driver.findElement(By.xpath("//li[@id='admin']"));
		Actions a = new Actions(driver);
		a.moveToElement(admintab).perform();
		
		/*mousehover to Project info*/
		WebElement proj = driver.findElement(By.xpath("//span[.='"+project+"']"));
		a.moveToElement(proj).perform();
		
		/*click on customer*/
		WebElement c = driver.findElement(By.xpath("//span[.='"+custom+"']"));
		a.click(c).perform();
		
		Thread.sleep(3000);
		/*click on add button*/
		driver.switchTo().frame("rightMenu");
		WebElement add = driver.findElement(By.id("btnAdd"));
	    add.click();
		
		/*Enter Customer Name*/
		driver.findElement(By.xpath("//input[@id='addCustomer_customerName']")).sendKeys(custname);
		
		/*Enter Customer description*/
		driver.findElement(By.id("addCustomer_description")).sendKeys(custdes);
		
		/*Click on Save button*/
		driver.findElement(By.id("btnSave")).click();
		
		/*Verify message*/
		WebElement msgg = driver.findElement(By.xpath("//span[.='"+msg+"']"));
		boolean msg1 = msgg.isDisplayed();
		if(msg1)
		{
			System.out.println(msgg.getText());
			Reporter.log(msgg.getText());
		}
		else{
			System.out.println("Failed to create customer");
		}
}
}