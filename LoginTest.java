package StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import PageObjects.LoginPage;
import cucumber.api.java.en.*;
import junit.framework.Assert;

public class LoginTest {
	public WebDriver driver;
	public LoginPage lp;
	
	@Given("User Launch Chrome browser")
	public void user_Launch_Chrome_browser() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		lp = new LoginPage(driver);
	}

	@When("URL is {string}")
	public void url_is(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}

	@When("User Enter Email as {string} and Password as {string}")
	public void user_Enter_Email_as_and_Password_as(String str, String string) {
		lp.setUserName(str);
		lp.setpswd(string);
	}

	@When("click on Login button")
	public void click_on_Login_button() {
		lp.loginClick();
	}

	@Then("Page title should be {string}")
	public void Page_title_should_be(String title) {
		String str1 = driver.getTitle();
		System.out.println(str1);
		Assert.assertEquals(title, str1);
	}

	@Then("Login Page title should be {string}")
	public void Login_Page_title_should_be(String title) {
		String str2 = driver.getTitle();
		System.out.println(str2);
		Assert.assertEquals(title, str2);
	}

	@When("user click on Logout button")
	public void user_click_on_Logout_button() {
		lp.logoutClick();
	}
	
	@Then("close browser")
	public void close_browser() {
		driver.close();
	}

}
