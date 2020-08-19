package com.pack.util;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Apputil {
	static DesiredCapabilities dc;

	public static AndroidDriver<WebElement> install_and_launch_apk(String Apk_path) throws MalformedURLException {

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		dc.setCapability(MobileCapabilityType.APP, Apk_path); // pass the path of the App
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		// dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0"); // you can
		// set the version for virtual device
		URL url = new URL("http://127.0.0.1:4723/wd/hub");// this is the appium server path
		AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(url, dc);
		return driver;
	}

	public static AndroidDriver<WebElement> launch_apk(String apk_package, String apk_activity)
			throws MalformedURLException {
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

}
