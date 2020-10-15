package com.tyss.Nyka.script;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
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

public class VerifyandDeleteProduct extends Filelib{
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



		/*Maximize window*/
		d.manage().window().maximize();

		/*Wait condition*/
		d.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		/*Navigate to the url*/
		d.get("https://www.nykaa.com/");

		/*Mousehover to Brand tab*/
		String brand = f.getExcelData("Nyka", 1, 0);
		WebElement brandtab = d.findElement(By.xpath("//a[text()='"+brand+"']"));
		Actions a = new Actions(d);
		a.moveToElement(brandtab).perform();

		/*Click on Brand Name*/
		String brandname = f.getExcelData("Nyka", 1, 1);
		d.findElement(By.xpath("(//a[text()='"+brandname+"'])[1]")).click();

		/*Click on Shop now*/
		d.findElement(By.xpath("(//div[@class='nw_grid_img'])[1]")).click();

		Set<String> allwh = d.getWindowHandles();
		Iterator<String> i = allwh.iterator();
		String pid = i.next();
		String cid = i.next();
		String cid1 = i.next();

		d.switchTo().window(cid1);

		WebDriverWait wb = new WebDriverWait(d,40);
		/*click on Filter 1*/
		String filter1 = f.getExcelData("Nyka", 1, 2);
		wb.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='"+filter1+"']"))).click();
		//d.findElement(By.xpath("")).click();

		/*click on a Range*/
		String filter1range = f.getExcelData("Nyka", 1, 3);
		wb.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[starts-with(text(),'"+filter1range+"')]"))).click();
		//d.findElement(By.xpath("//span[starts-with(text(),'"+filter1range+"')]")).click();

		/*Click on filter2*/
		String filter2 = f.getExcelData("Nyka", 1, 4);
		wb.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='"+filter2+"']"))).click();
		//d.findElement(By.xpath("//div[text()='"+filter2+"']")).click();

		/*click on filter2 range*/
		String filter2range = f.getExcelData("Nyka", 1, 5);
		wb.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[starts-with(text(),'"+filter2range+"')]"))).click();
		//d.findElement(By.xpath("//span[starts-with(text(),'"+filter2range+"')]")).click();

		/*Click on filter3*/
		String filter3 = f.getExcelData("Nyka", 1, 6);
		
		wb.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='"+filter3+"']"))).click();
		//d.findElement(By.xpath("//div[text()='"+filter3+"']")).click();

		/*Select the Filter range*/
		String filter3range = f.getExcelData("Nyka", 1, 7);
		wb.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'"+filter3range+"')]"))).click();
		//d.findElement(By.xpath("//span[contains(text(),'"+filter3range+"')]")).click();

		/*click on add to cart*/
		WebElement prod = d.findElement(By.xpath("(//img[@class='listing-img'])[1]"));
		a.moveToElement(prod).perform();
		WebElement addtocart = d.findElement(By.xpath("(//button[@class='primary-btn nk-btn combo-add-to-btn luxury_plpButton atc-simple m-content__product-list__cart-btn  '])[1]"));
		a.moveToElement(addtocart).click().perform();

		/*fetch the actual text of the product*/
		String actualpname = f.getExcelData("Nyka", 1, 8);
		WebElement actualname = d.findElement(By.xpath("//span[text()='"+actualpname+"']"));
		
		
		/*Click on Cart icon*/
		d.findElement(By.xpath("//div[@class='AddBagIcon']")).click();
		
		/*get the product name from cart*/
		String cartname = f.getExcelData("Nyka", 1, 9);
		WebElement nameincart = d.findElement(By.xpath("//div[text()='"+cartname+"']"));
		
	
		List<WebElement> allitem = d.findElements(By.xpath("//div[@class='product-name']"));
		boolean flog = false;
		for(WebElement all:allitem)
		{
			String actualitem = all.getText();
			if(actualitem.equalsIgnoreCase(cartname))
				/*Click on Delete Button*/
				wb.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='remove-product']"))).click();
				//d.findElement(By.xpath("//i[@class='remove-product']")).click();
				/*Click on Yes button*/
				wb.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button' and text()='Yes']"))).click();
				//d.findElement(By.xpath("//button[@type='button' and text()='Yes']")).click();
			
				System.out.println("Product is Successfully removed from the cart");
			    flog=true;

		}
		if(!flog){
			System.out.println("Product is not removed from the cart");
		}
			
	}
}