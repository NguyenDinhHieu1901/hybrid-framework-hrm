package datadriventesting;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ReadExcelFile;

public class Example1 {
	private WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://demo.nopcommerce.com/");
	}

	@Test(priority = 1, description = "Verify login function of system", enabled = true, dataProvider = "login")
	public void TC_01_Login(String userName, String password) throws InterruptedException {
		driver.findElement(By.cssSelector("a.ico-login")).click();
		driver.findElement(By.cssSelector("input#Email")).sendKeys(userName);
		driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
		Thread.sleep(3000);
		Assert.assertTrue(driver.getTitle().matches("nopCommerce demo store"));
		System.out.println("Login successful");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}

	@DataProvider(name = "login")
	public Object[][] loginData() {
		System.out.println(System.getProperty("user.dir") + File.separator + "login.xlsx");
		ReadExcelFile configuration = new ReadExcelFile(System.getProperty("user.dir") + File.separator + "login.xlsx" + File.separator);
		int row = configuration.getRowCount(0);
		Object[][] signin_credentials = new Object[row][2];

		for (int i = 0; i < row; i++) {
			signin_credentials[i][0] = configuration.getData(0, i, 0);
			signin_credentials[i][1] = configuration.getData(0, i, 1);
		}
		return signin_credentials;
	}

}
