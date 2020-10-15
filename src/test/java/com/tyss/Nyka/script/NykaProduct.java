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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tyss.Bluestone1.generics.Filelib;

public class NykaProduct {
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

		WebDriverWait wb = new WebDriverWait(d, 50);
		WebElement skintab = d.findElement(By.xpath("//li[@class='MegaDropdownHeadingbox']/../li[2]"));
		Actions a = new Actions(d);
		a.moveToElement(skintab).perform();

		String skincategory = f.getExcelData("Nykaa1", 1, 0);
		d.findElement(By.xpath("//a[text()='"+skincategory+"']")).click();

		Set<String> allwh = d.getWindowHandles();
		Iterator<String> i = allwh.iterator();
		String pid = i.next();
		String cid = i.next();
		String cid1 = i.next();

		/*switch browser control*/
		d.switchTo().window(cid1);
		/*select the brand */
		String brandfilter = f.getExcelData("Nykaa1", 1, 1);
		wb.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='"+brandfilter+"']"))).click();
		//d.findElement(By.xpath("//div[text()='Brand']")).click();

		/*select the brand range*/
		String filter1range = f.getExcelData("Nykaa1", 1, 2);
		d.findElement(By.xpath("//div[@class='control-box']//following-sibling::label/span[starts-with(text(),'"+filter1range+"')]")).click();

		/*select the price*/
		String pricefilter = f.getExcelData("Nykaa1", 1, 3);
		wb.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='"+pricefilter+"']"))).click();

		/*select the price range*/
		String filter2range = f.getExcelData("Nykaa1", 1, 4);
		d.findElement(By.xpath("//div[@class='control-box']//following-sibling::label/span[starts-with(text(),'"+filter2range+"')]")).click();

		/*select the category*/
		String concernfilter = f.getExcelData("Nykaa1", 1, 5);
		wb.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='"+concernfilter+"']"))).click();

		String filter3range = f.getExcelData("Nykaa1", 1, 6);
		d.findElement(By.xpath("//div[@class='control-box']//following-sibling::label/span[starts-with(text(),'"+filter3range+"')]")).click();

		/*mouse hover to the first product*/
		WebElement product = d.findElement(By.xpath("(//div[@class='card-img'])[1]"));
		a.moveToElement(product).perform();

		/*click on preview button*/
		wb.until(ExpectedConditions.elementToBeClickable(d.findElement(By.xpath("(//span[text()='PREVIEW SIZE'])[1]")))).click();

		String size = f.getExcelData("Nykaa1", 1, 7);
		d.findElement(By.xpath("//span[text()='"+size+"']")).click();

		/*click on Add to cart button*/
		d.findElement(By.xpath("//div[@class='col-xs-6 no-style']/button")).click();

		WebElement appliancetab = d.findElement(By.xpath("//li[@class='MegaDropdownHeadingbox']/../li[4]"));
		a.moveToElement(appliancetab).perform();

		String appliancecategory= f.getExcelData("Nykaa1", 1, 8);
		d.findElement(By.xpath("//a[contains(text(),'"+appliancecategory+"')]")).click();

		/*Set<String> allwh1 = d.getWindowHandles();
		Iterator<String> i1 = allwh1.iterator();
		String pid1 = i.next();
		String cid2 = i.next();
		String cid3 = i.next();

		d.switchTo().window(cid3);*/
		/*select the appliance*/
		String filter1 = f.getExcelData("Nykaa1", 1, 9);
		wb.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='"+filter1+"']"))).click();
		//d.findElement(By.xpath("//div[text()='Brand']")).click();

		/*click on a appliance*/
		String filter4range = f.getExcelData("Nykaa1", 1, 10);
		d.findElement(By.xpath("//div[@class='control-box']//following-sibling::label/span[starts-with(text(),'"+filter4range+"')]")).click();

		/*select the first product*/
		WebElement pproduct = d.findElement(By.xpath("(//div[@class='col-xs-12 card-img-container '])[1]"));
		a.moveToElement(pproduct).perform();
		/*add to cart*/
		WebElement addtocart1 = d.findElement(By.xpath("(//button[@class='primary-btn nk-btn combo-add-to-btn  atc-simple m-content__product-list__cart-btn  '])[1]"));
		a.moveToElement(addtocart1).click().perform();

		d.findElement(By.xpath("//div[@class='AddToBagbox']/div/div")).click();
		//wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")); 
		WebElement freeprod = d.findElement(By.xpath("(//div[@class='product-name'])[1]"));
		String free = freeprod.getText();
		WebElement philips = d.findElement(By.xpath("(//div[@class='product-name'])[2]"));
		String item2 = philips.getText();
		WebElement fairnesscream = d.findElement(By.xpath("(//div[@class='product-name'])[3]"));
		String fair = fairnesscream.getText();
		List<WebElement> allitem = d.findElements(By.xpath("//div[@class='product-name']"));
		boolean flog = false;
		for(WebElement all:allitem)
		{
			String actualitem = all.getText();
			if(actualitem.equalsIgnoreCase(free))
			{
				System.out.println("Free item is present");
			}
			if(actualitem.equalsIgnoreCase(item2))
			{
				System.out.println("Philips product is present");
			}

			if(actualitem.equalsIgnoreCase(fair))
			{
				System.out.println("fairness product is present");
			}

		}
	}
}