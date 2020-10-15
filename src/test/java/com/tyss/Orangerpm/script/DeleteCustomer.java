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

public class DeleteCustomer {
	static
	{
		System.setProperty("webdriver.chrome.driver", "./dr/chromedriver.exe");
	}
	@Test

	public void DeleteCustomer() throws EncryptedDocumentException, InvalidFormatException, IOException
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
		String custname = f.getExcelData("Orangehrm", 2, 4);

		/*fetch data from 1st row 6th column*/
		String custdes = f.getExcelData("Orangehrm", 2, 5);

		/*fetch data from 2nd row 7th column*/
		String msg = f.getExcelData("Orangehrm", 2, 6);
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

		/*click on add button*/
		driver.switchTo().frame("rightMenu");
		driver.findElement(By.id("btnAdd")).click();

		/*Enter Customer Name*/
		driver.findElement(By.xpath("//input[@id='addCustomer_customerName']")).sendKeys(custname);

		/*Enter Customer description*/
		driver.findElement(By.id("addCustomer_description")).sendKeys(custdes);

		/*Click on Save button*/
		driver.findElement(By.id("btnSave")).click();


		/*select the checkbox of the name*/
		driver.findElement(By.xpath("//a[.='"+custname+"']/../..//input[@type='checkbox' and @name='chkSelectRow[]']")).click();

		/*click on delete button*/
		driver.findElement(By.id("btnDelete")).click();

		/*Click on Ok button in the dialogue box*/
		driver.findElement(By.xpath("//input[@id='dialogDeleteBtn']")).click();

		WebElement msg1 = driver.findElement(By.xpath("//span[.='"+msg+"']"));
		boolean msg2 = msg1.isDisplayed();

		if(msg2)
		{
			System.out.println(msg1.getText()+" displayed");
		}
		else
		{
			System.out.println("Could not delete the customer");
		}
	}
}
