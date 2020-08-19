package com.pack.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

import com.google.common.io.Files;

public class Webutil {

	static WebDriver driver;

	public static WebDriver browserLaunch(String BrowserName) {

		if (BrowserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", ".//exe//chromedriverlatest.exe");
			driver = new ChromeDriver();
		} else if (BrowserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", ".//exe//geckodriver.exe");
			// driver= new FirefoxDriver();
		} else if (BrowserName.equalsIgnoreCase("opera")) {
			System.setProperty("webdriver.opera.driver", ".//exe//operadriver.exe");
			driver = new InternetExplorerDriver();
		} else {
			System.out.println("Browser Name is Wrong");
		}
		driver.manage().window().maximize();
		return driver;
	}

	public static void click(WebElement ele) {
		ele.click();
	}

	public static void sendkeys(WebElement ele, String txt) {
		ele.sendKeys(txt);
	}

	public static void open_url(String URL) {
		driver.get(URL);
	}

	public static void refreshpage() {
		driver.navigate().refresh();
	}

	public static void switchWindowFocusByTitle(String title) {
		try {
			Set<String> handleValues = driver.getWindowHandles();
			for (String handleValue : handleValues) {
				driver.switchTo().window(handleValue);
				if (driver.getTitle().trim().equalsIgnoreCase(title)) {
					break;
				}
			}
		} catch (Exception e) {
			// logger.debug( Constants.ELEMENT_SEARCH_ERROR_MESSAGE, e);
		}
	}

	public static void close_window() {
		driver.close();
	}

	public static String takeScreenShot(String testName) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destFile = null;
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh_mm_ss");
			String timeStamp = formatter.format(calendar.getTime());

			destFile = new File(System.getProperty("user.dir") + File.separator + "test-output" + File.separator
					+ testName + "_" + timeStamp + ".png");
			Files.copy(scrFile, destFile);
		} catch (Exception e) {

		}
		return destFile.getAbsolutePath();
	}

	public static void selectbyvalue(WebElement ele, String value) {
		Select sc = new Select(ele);
		sc.selectByValue(value);

	}
}
