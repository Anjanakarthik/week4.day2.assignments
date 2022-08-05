package week4.day2.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class ArhitectCertifications {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		//Login
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20000));
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Password$123");
		driver.findElement(By.id("Login")).click();

		//Learn More
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();
		Set<String>WindowHandles = driver.getWindowHandles();
		List<String>lstwindowhandles=new ArrayList<String>(WindowHandles);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		//Accept the message
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		driver.switchTo().window(lstwindowhandles.get(1));
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		driver.findElement(By.xpath("//a[text()='Resources']")).click();

		//shadowdom elements
		Shadow dom = new Shadow(driver);
		WebElement Learning=dom.findElementByXPath("//span[text()='Learning']");
		Learning.click();
		WebElement ltrailhead=dom.findElementByXPath("//span[contains(text(),'Learning on Trailhead')]");
		Actions builder = new Actions(driver);
		builder.scrollToElement(ltrailhead);
		ltrailhead.click();

		//certification click
		WebElement cerification=dom.findElementByXPath("//a[text()='Salesforce Certification']");

		try {
			Thread.sleep(3000);
			builder.scrollToElement(cerification).perform();
			cerification.click();
		}catch(Exception e) {
			builder.scrollToElement(cerification).perform();
			wait.until(ExpectedConditions.elementToBeClickable(cerification));
			cerification.click();
		}
		System.out.println(driver.findElement(By.xpath("(//div[@class='roleMenu-item_text'])[2]")).getText());
		driver.findElement(By.xpath("(//div[@class='roleMenu-item_text'])[2]")).click();

		//salesforce certifications
		List<WebElement>numofcertifications = driver.findElements(By.xpath("//div[@class='credentials-card_title']"));
		System.out.println("Number of certifications availble for architect are "+numofcertifications.size());
		System.out.println("Salesforce crtifications are");
		for(int j=0;j<numofcertifications.size();j++) {
			System.out.println(numofcertifications.get(j).getText());
		}
		
		

		//application architect certifications
		WebElement archcertification=driver.findElement(By.xpath("//a[text()='Application Architect']"));
		builder.scrollToElement(archcertification).perform();
		archcertification.click();
		List<WebElement>archcertifications=driver.findElements(By.xpath("//div[@class='credentials-card_title']"));
		for(int i=0;i<archcertifications.size();i++) {
			wait.until(ExpectedConditions.visibilityOfAllElements(archcertifications));
			System.out.println(archcertifications.get(i).getText());
		}
		//System.out.println(archcertifications);
	}

}
