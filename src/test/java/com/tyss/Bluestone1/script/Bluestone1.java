package com.tyss.Bluestone1.script;


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

public class Bluestone1 extends Filelib{
	
	static
	{
	        System.setProperty("webdriver.chrome.driver", "./dr/chromedriver.exe");
	}
	@Test
	
	public void Bluestone() throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		Filelib f = new Filelib();
		
		/*fetch data from 1st row 1st column*/
		String Ringtype = f.getExcelData("Sheet1", 1, 0);
		
		/*fetch data from 1st row 2nd column*/
		String pricerange = f.getExcelData("Sheet1", 1, 1);
		
		/*fetch data from 1st row 3rd column*/
		String sort = f.getExcelData("Sheet1", 1, 2);
		
		/*Launch chromebrowser*/
		WebDriver driver = new ChromeDriver();
		
		/*Manage timeouts*/
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		/*Maximize browser*/
		driver.manage().window().maximize();
		
		/* Bluestone url*/
		driver.get("https://www.bluestone.com/");
		
		/*Mouse over on Rings*/
		WebElement rings = driver.findElement(By.xpath("//li[@class='menuparent repb']/../li[2]"));
		Actions a= new Actions(driver);
		a.moveToElement(rings).perform();
		
		/*Select the ring type*/
		driver.findElement(By.xpath("//a[.='"+Ringtype+"'] ")).click();
         WebElement price = driver.findElement(By.xpath("//div[@class='top-filter-blocks']/../div[2]"));
         a.moveToElement(price).perform();
         
         /*select the price range*/
		driver.findElement(By.xpath("//span[@class='prcs-dlh' and @data-displayname='"+pricerange+"']")).click();

		/*select from low to high filter*/
		WebElement dd = driver.findElement(By.xpath("(//i[@class='icon-ion-chevron-down'])[8]"));
		a.moveToElement(dd).perform();
		driver.findElement(By.xpath("//a[text()='"+sort+"']")).click();
		
		/*Click on the product*/
		driver.findElement(By.xpath("(//ul[@class='product-grid ']//following-sibling::li[1])[1]")).click();
		System.out.println("start");
		/*details of the selected ring*/
		/*WebElement details = driver.findElement(By.xpath("//h2[@class='title expanded']/../div/dl/dt"));
		String d1 = details.getText();
		System.out.println(d1);
		for(int i=0;i<4;i++)
		{
			String detail = details.get(i).getText();
			System.out.println(detail+" done");
		}*/
		
		WebElement pd = driver.findElement(By.xpath("//h2[text()='Product Details']"));
		System.out.println(pd.getText());
		System.out.println(driver.findElement(By.xpath("//dt[text()='Product Code']")).getText() + " --> "+driver.findElement(By.id("detail-product-code")).getText());
		
		System.out.println(driver.findElement(By.xpath("//dt[text()='Height']")).getText() +" --> "+ driver.findElement(By.xpath("//dt[text()='Height']/following-sibling::dd[@class='last']")).getText());
		
		
		System.out.println("stop");
	}
	


	

}


