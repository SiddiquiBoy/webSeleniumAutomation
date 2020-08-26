package com.pack.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.google.common.io.Files;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Apputil {

	public AndroidDriver<WebElement> driver;
	public DesiredCapabilities dc;
	String folder_name;
    DateFormat df;

	public void install_and_launch_apk(String Apk_path) throws MalformedURLException {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		dc.setCapability(MobileCapabilityType.APP, Apk_path); // pass the path of the App
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		// dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0"); // you can
		// set the version for virtual device
		URL url = new URL("http://127.0.0.1:4723/wd/hub");// this is the appium server path
		driver = new AndroidDriver<WebElement>(url, dc);

	}

	public AndroidDriver<WebElement> launch_apk(String apk_package, String apk_activity) throws MalformedURLException {
		dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		dc.setCapability("appPackage", apk_package);
		dc.setCapability("appActivity", apk_activity);

		URL url = new URL("http://127.0.0.1:4723/wd/hub");// this is the Appium server path
		AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(url, dc);
		return driver;

	}

	public void click(WebElement ele) {
		ele.click();
	}

	public void sendkeys(WebElement ele, String txt) {
		ele.sendKeys(txt);
	}

	public void handle_dropdown(WebElement dropdown, String value) throws InterruptedException {

		//driver.findElementById("android:id/text1").click();// click on dropdown
        dropdown.click();
		List<WebElement> options = driver.findElementsById("android:id/text1");
		//System.out.println("Total number of options available in dropdown:" + options.size());
		for (WebElement e : options) {
			String val = e.getText();
			if (val.equalsIgnoreCase(value)) {
				e.click();
				break;
			}
		}
		Thread.sleep(8000);
	}
	
	public void captureScreenShots(String file_name) throws IOException {
        folder_name="screenshot";
        File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //create dir with given folder name
        new File(folder_name).mkdir();
        //Setting file name
        file_name=df.format(new Date())+".png";
        //coppy screenshot file into screenshot folder.
        Files.copy(f,new File(folder_name + "/" + file_name));
    }
	

}
