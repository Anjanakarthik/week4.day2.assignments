package week4.day2.assignments;

import java.sql.DriverPropertyInfo;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.Action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class AdministratorCertifications {

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
		wait.until(ExpectedConditions.visibilityOf(Learning));
		Learning.click();
		WebElement ltrailhead=dom.findElementByXPath("//span[contains(text(),'Learning on Trailhead')]");
		Actions builder = new Actions(driver);
		builder.scrollToElement(ltrailhead);
		ltrailhead.click();

		WebElement cerification=dom.findElementByXPath("//a[text()='Salesforce Certification']");
		

		//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		//		wait.until(ExpectedConditions.elementToBeClickable(cerification));
		try {
			Thread.sleep(3000);
			builder.scrollToElement(cerification).perform();
			cerification.click();


		}catch(Exception e) {
			
			builder.scrollToElement(cerification).perform();
			wait.until(ExpectedConditions.elementToBeClickable(cerification));

			cerification.click();
		}
		//click on certification administrator
		WebElement administrator=driver.findElement(By.xpath("//a[text()='Administrator']"));
		builder.scrollToElement(administrator).perform();
		administrator.click();
//		WebElement credentials=dom.findElementByXPath("//button[text().'Credentials']");
//		wait.until(ExpectedConditions.visibilityOf(credentials));
//		
//		wait.until(ExpectedConditions.elementToBeClickable(credentials));
		//builder.scrollToElement(credentials).perform();
//	WebElement	cert = driver.findElement(By.xpath("//div[text()='Certifications']"));
//	wait.until(ExpectedConditions.visibilityOf(cert));
//	cert.click();
//	WebElement admin=driver.findElement(By.xpath("//a[text()='Administrator']"));
//	wait.until(ExpectedConditions.visibilityOf(admin));
//	
//	builder.scrollToElement(admin).perform();
//	admin.click();
//	WebElement certifications=driver.findElement(By.xpath("//a[text()='Salesforce Trailhead']"));
	
//	
WebElement Certifications=driver.findElement(By.xpath("//div[text()='Certification']"));
if(Certifications.isDisplayed()) {
	System.out.println("Certifictions are listed");
}
		
		

	}


}
