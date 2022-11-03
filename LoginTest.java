package StepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import PageObjects.AddCustomerPage;
import PageObjects.LoginPage;
import PageObjects.SearchCustomer;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import junit.framework.Assert;

public class LoginTest extends BaseClass {
	
	@Before
	public void setUp() throws IOException {
		configprop = new Properties();
		FileInputStream configpropfile = new FileInputStream("config.properties");
		configprop.load(configpropfile);
		logger = Logger.getLogger("nopcommerce");
		PropertyConfigurator.configure("log4j.properties");
		String br = configprop.getProperty("browser");
		if(br.equals("chrome")){
		System.setProperty("webdriver.chrome.driver", configprop.getProperty("chromepath"));
		driver = new ChromeDriver();
		}
	}

	@Given("User Launch Chrome browser")
	public void user_Launch_Chrome_browser() {
	
		logger.info("Launching Browser");
		lp = new LoginPage(driver);
	}

	@When("URL is {string}")
	public void url_is(String url) {
		logger.info("Opening URL");
		driver.get(url);
		driver.manage().window().maximize();
	}

	@When("User Enter Email as {string} and Password as {string}")
	public void user_Enter_Email_as_and_Password_as(String str, String string) {
		logger.info("Providing Username and Password");
		lp.setUserName(str);
		lp.setpswd(string);
	}

	@When("click on Login button")
	public void click_on_Login_button() {
		logger.info("Clicking Login Button");
		lp.loginClick();
	}

	@Then("Page title should be {string}")
	public void Page_title_should_be(String title) {
		logger.info("Verifying page Title");
		String str1 = driver.getTitle();
		System.out.println(str1);
		Assert.assertEquals(title, str1);
	}

	@Then("Login Page title should be {string}")
	public void Login_Page_title_should_be(String title) {
		logger.info("Verifying page Title2");
		String str2 = driver.getTitle();
		System.out.println(str2);
		Assert.assertEquals(title, str2);
	}

	@When("user click on Logout button")
	public void user_click_on_Logout_button() {
		logger.info("Logout Button");
		lp.logoutClick();
	}

// Customer feature step definition
	@Then("User can view Dashboard")
	public void user_can_view_Dashboard() {
		logger.info("Verifying Dashboard");
		addCust = new AddCustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
	}

	@When("user click on Customer icon")
	public void user_click_on_Customer_icon() {
		logger.info("Click Customer Icon");
		addCust.customerIcon();
	}

	@When("Click on Customer menu item")
	public void click_on_Customer_menu_item() {
		logger.info("Click Customer Menu");
		addCust.customerMenu();
	}

	@When("click on AddNew button")
	public void click_on_AddNew_button() {
		logger.info("Click Add Button");
		addCust.addButton();
	}

	@Then("user can view Add new customer page")
	public void user_can_view_Add_new_customer_page() {
		logger.info("View Customr Page");
		Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	}

	@When("user enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		logger.info("Adding Customer");
		String email = randomeString() + "@gmail.com";
		addCust.setEmail(email);
		addCust.setPassword("Sai@12345");
		addCust.setFirstName("Sai");
		addCust.setLastName("Ch");
		addCust.setGender();
		addCust.DateBirth();
		addCust.companyName("IVTL");
		addCust.tax();
		addCust.letter("Test store 2");
		addCust.setCustomerRoles("Guests");
		addCust.setVendor("Vendor 1");
		addCust.setActive();
		addCust.setAdmin("Customer Adding Completed");
	}

	@When("click on save button")
	public void click_on_save_button() throws InterruptedException {
		logger.info("Save Customer");
		addCust.save();
		Thread.sleep(2000);

	}

	@Then("user can view confirmation message {string}")
	public void user_can_view_confirmation_message(String string) {
		logger.info("View Confirmation Message");
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
				.contains("The new customer has been added successfully"));
	}

	// search by email

	@When("user enter customer email")
	public void user_enter_customer_email() {
		logger.info("Enter Customer Email");
		searchCustomer = new SearchCustomer(driver);
		searchCustomer.setEmail("anSVi@gmail.com");
	}

	@When("click search button")
	public void click_search_button() throws InterruptedException {
		logger.info("Click on Search Button");
		searchCustomer.customers();
		Thread.sleep(2000);
	}

	@Then("search record should be displayed in table")
	public void search_record_should_be_displayed_in_table() {
		logger.info("Validating email Record");
		boolean status = searchCustomer.searchCustomerByEmail("anSVi@gmail.com");
		Assert.assertEquals(true, status);
	}

	// search by Name

	@When("user enter customer first Name")
	public void user_enter_customer_first_Name() {
		searchCustomer = new SearchCustomer(driver);
		logger.info("Enter Customer Fname");
		searchCustomer.firstName("Sai");
	}

	@When("user enter customer last Name")
	public void user_enter_customer_last_Name() {
		logger.info("Enter Customer Lname");
		searchCustomer.lastName("Ch");
	}

	@Then("search name should be displayed in table")
	public void search_name_should_be_displayed_in_table() {
		logger.info("Validating Name Record");
		boolean status = searchCustomer.searchCustomerByName("Sai Ch");
		Assert.assertEquals(true, status);
	}

	// search by Company

	@When("user enter customer Company")
	public void user_enter_customer_Company() {
		searchCustomer = new SearchCustomer(driver);
		logger.info("Enter Customer Comapny");
		searchCustomer.searchCompany("IVTL");
	}

	@Then("search Company should be displayed in table")
	public void search_Company_should_be_displayed_in_table() {
		logger.info("Validating Comapny Record");
		boolean status = searchCustomer.searchCustomerByCompany("IVTL");
		Assert.assertEquals(true, status);
	}

	// search by DateOfBirth

	@When("user enter customer Month")
	public void user_enter_customer_Month() {
		searchCustomer = new SearchCustomer(driver);
		logger.info("Enter Customer Month");
		searchCustomer.searchMonthOfBirth("12");
	}

	@When("user enter customer Date")
	public void user_enter_customer_Date() {
		logger.info("Enter Customer Date");
		searchCustomer.searchDayOfBirth("5");
	}
	
	// search by CustomerRole

	@When("user enter CustomerRole")
	public void user_enter_CustomerRole() {
		searchCustomer = new SearchCustomer(driver);
		logger.info("Enter Customer Role");
		searchCustomer.setCustomerRoles("Registered");
	}

	@Then("search CustomerRole should be displayed in table")
	public void search_CustomerRole_should_be_displayed_in_table() {
		logger.info("Validating Customer Role Record");
		boolean status = searchCustomer.searchCustomerByRole("Registered");
		Assert.assertEquals(true, status);
	}

	@Then("close browser")
	public void close_browser() {
		logger.info("Closing Browser");
		driver.close();
	}

}
