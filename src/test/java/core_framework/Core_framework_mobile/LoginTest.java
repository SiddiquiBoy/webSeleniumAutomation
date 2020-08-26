package core_framework.Core_framework_mobile;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pack.pages.LoginPage;
import com.pack.util.Apputil;

public class LoginTest extends Apputil{

	public LoginPage login_obj;
	public String apk_package="";
	public String apk_activity="";
	
	@BeforeMethod
	public void setup() throws MalformedURLException
	{
		
		launch_apk(apk_package, apk_activity);
		login_obj =new LoginPage();
	}
	
	@Test(description="As a User I should be able to login successfully",priority=1)
	public void validlogin() throws InterruptedException
	{
		boolean result = login_obj.valid_login("abc@gmail.com", "password");
		assertEquals(result, true);
		
	}
	
	@AfterMethod
	public void teardown()
	{
		
		driver.quit();
	}
}
