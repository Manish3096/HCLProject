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
import org.testng.annotations.Test;

import com.tyss.Bluestone1.generics.Filelib;

public class CreateProject {
	static
	{
		System.setProperty("webdriver.chrome.driver", "./dr/chromedriver.exe");
	}
	@Test

	public void CreateProject() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		Filelib f = new Filelib();

		/*fetch data from 1st row 1st column*/
		String userid = f.getExcelData("Orangehrm", 1, 0);

		/*fetch data from 1st row 2nd column*/
		String password = f.getExcelData("Orangehrm", 1, 1);

		/*fetch data from 1st row 3rd column*/
		String project = f.getExcelData("Orangehrm", 1, 2);

		/*fetch data from 1st row 4th column*/
		String custom = f.getExcelData("Orangehrm", 3, 3);

		/*fetch data from 3rd row 2nd column*/
		String custnamelink = f.getExcelData("Orangehrm", 3, 1);

		/*fetch data from 3rd row 5th column*/
		String cusname = f.getExcelData("Orangehrm", 3, 4);

		/*fetch data from 3rd row 6th column*/
		String custdes = f.getExcelData("Orangehrm", 3, 5);

		/*fetch data from 4th row 6th column*/
		String projname = f.getExcelData("Orangehrm", 4, 5);

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

		/*click on Projects*/
		WebElement c = driver.findElement(By.xpath("//span[text()='"+custom+"']"));
		a.click(c).perform();

		Thread.sleep(3000);
		/*click on add button*/
		driver.switchTo().frame("rightMenu");
		WebElement add = driver.findElement(By.id("btnAdd"));
		add.click();

		/*Click on Add customer link */
		driver.findElement(By.xpath("//a[text()='"+custnamelink+"']")).click();

		/*Enter Customer Name*/
		driver.findElement(By.xpath("//input[@id='addCustomer_customerName']")).sendKeys(cusname);

		/*Enter Customer description*/
		driver.findElement(By.id("addCustomer_description")).sendKeys(custdes);

		/*Click on Save button*/
		driver.findElement(By.id("btnSave")).click();

		/*Enter Project Name*/
		driver.findElement(By.id("addProject_projectName")).sendKeys(projname);

		driver.findElement(By.id("btnSave")).click();

		WebElement msg1 = driver.findElement(By.xpath("//span[.='"+msg+"']"));
		boolean ms = msg1.isDisplayed();
		if(ms)
		{
			System.out.println(msg1+": pass");

		}
		else
		{
			System.out.println("Failed to create Project");
		}
	}

}
 