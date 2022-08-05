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

public class CustomerServiceOptions {

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

		//shadowroot elements
		Shadow dom =new Shadow(driver);
		WebElement products = dom.findElementByXPath("//span[text()='Products']");
		wait.until(ExpectedConditions.visibilityOf(products));
		products.click();
		WebElement service=dom.findElementByXPath("//span[text()='Service']");

		//Action class
		Actions builder =new Actions(driver);
		builder.moveToElement(service);

		//List of customer services
		WebElement custservise=dom.findElementByXPath("//a[text()='Customer Service']");
		builder.scrollToElement(custservise).perform();
		wait.until(ExpectedConditions.elementToBeClickable(custservise));
		custservise.click();
		System.out.println("List of customer services are");
		List<WebElement>services = driver.findElements(By.xpath("//a[@class='page-list-item ']"));
		for(int i =0;i<services.size();i++) {

			System.out.println(services.get(i).getText());
			//title
	System.out.println(driver.getTitle());

		}

	}

}
