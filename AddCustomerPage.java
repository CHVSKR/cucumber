package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Utilities.ScreenShots;
import Utilities.WaitHelper;

public class AddCustomerPage {
	public WebDriver ldriver;
	WaitHelper helper;
	ScreenShots screenShot;

	public AddCustomerPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
		helper = new WaitHelper(ldriver);
		screenShot = new ScreenShots();
	}

	@FindBy(xpath = "//div/nav/ul/li[4]/a[@href='#']")
	@CacheLookup
	WebElement customerIcon;

	@FindBy(xpath = "//div/nav/ul/li[4]/ul/li[1]/a/p")
	@CacheLookup
	WebElement customerMenu;

	@FindBy(xpath = "//form/div/div/a")
	@CacheLookup
	WebElement addButton;

	@FindBy(xpath = "//input[@id='Email']")
	@CacheLookup
	WebElement inputId;

	@FindBy(xpath = "//input[@id='Password']")
	@CacheLookup
	WebElement inputPassword;

	@FindBy(xpath = "//input[@id='FirstName']")
	@CacheLookup
	WebElement firstName;

	@FindBy(xpath = "//input[@id='LastName']")
	@CacheLookup
	WebElement lastName;

	@FindBy(xpath = "//input[@id='Gender_Male']")
	@CacheLookup
	WebElement genderMale;

	@FindBy(xpath = "//input[@id='Gender_Female']")
	@CacheLookup
	WebElement genderFemale;

	@FindBy(xpath = "//span[@class='k-icon k-i-calendar']")
	@CacheLookup
	WebElement dateOfBirth;

	@FindBy(xpath = "//input[@id='Company']")
	@CacheLookup
	WebElement companyName;

	@FindBy(xpath = "//input[@id='IsTaxExempt']")
	@CacheLookup
	WebElement tax;

	@FindBy(xpath = "//*[@id='customer-info']/div[2]/div[9]/div[2]/div/div[1]/div/div")
	@CacheLookup
	WebElement letter;

	@FindBy(xpath = "//ul[@id='SelectedNewsletterSubscriptionStoreIds_listbox']/li[2]")
	@CacheLookup
	WebElement testStore;

	@FindBy(xpath = "//*[@id='customer-info']/div[2]/div[10]/div[2]/div/div[1]/div/div")
	@CacheLookup
	WebElement customerRole;

	@FindBy(xpath = "//ul[@id='SelectedCustomerRoleIds_listbox']/li[1]")
	@CacheLookup
	WebElement lstitemAdministartor;

	@FindBy(xpath = "//ul[@id='SelectedCustomerRoleIds_listbox']/li[2]")
	@CacheLookup
	WebElement lstitemForum;

	@FindBy(xpath = "//ul[@id='SelectedCustomerRoleIds_listbox']/li[3]")
	@CacheLookup
	WebElement lstitemGuests;

	@FindBy(xpath = "//ul[@id='SelectedCustomerRoleIds_listbox']/li[4]")
	@CacheLookup
	WebElement lstitemRegistered;

	@FindBy(xpath = "//ul[@id='SelectedCustomerRoleIds_listbox']/li[5]")
	@CacheLookup
	WebElement lstitemVendor;

	@FindBy(xpath = "//select[@id='VendorId']")
	@CacheLookup
	WebElement vendorId;
	@FindBy(xpath = "//input[@id='Active']")
	@CacheLookup
	WebElement active;

	@FindBy(xpath = "//textarea[@id='AdminComment']")
	@CacheLookup
	WebElement adminComment;

	@FindBy(xpath = "//button[@name='save']")
	@CacheLookup
	WebElement save;

	public String getPageTitle() {
		return ldriver.getTitle();

	}

	public void customerIcon() {
		helper.waitForElement(customerIcon, 10);
		customerIcon.click();
		ScreenShots.captureScreenshot(ldriver, "customerIcon");
	}

	public void customerMenu() {
		helper.waitForElement(customerMenu, 10);
		customerMenu.click();
		ScreenShots.captureScreenshot(ldriver, "customerMenu");
	}

	public void addButton() {
		helper.waitForElement(addButton, 10);
		addButton.click();
		ScreenShots.captureScreenshot(ldriver, "addButton");
	}

	public void setEmail(String email) {
		inputId.sendKeys(email);
		ScreenShots.captureScreenshot(ldriver, "setEmail");
	}

	public void setPassword(String password) {
		inputPassword.sendKeys(password);
		ScreenShots.captureScreenshot(ldriver, "setPassword");
	}

	public void setFirstName(String fName) {
		firstName.sendKeys(fName);
		ScreenShots.captureScreenshot(ldriver, "setFirstName");
	}

	public void setLastName(String lName) {
		lastName.sendKeys(lName);
		ScreenShots.captureScreenshot(ldriver, "setLastName");
	}

	public void setGender() {
		helper.waitForElement(genderMale, 10);
		genderMale.click();
		ScreenShots.captureScreenshot(ldriver, "setGender");
	}

	public void DateBirth() throws InterruptedException {
		dateOfBirth.click();
		String years = "2022";
		String month = "December";
		String date = "5";
		while (true) {
			String monthYear = ldriver.findElement(By.xpath("//div[@class='k-header']/a[2]")).getText();
			System.out.println(monthYear);
			String arr[] = monthYear.split(" ");
			Thread.sleep(2000);
			String mon = arr[0];
			String year = arr[1];
			Thread.sleep(2000);
			if (mon.equals(month) && year.equals(years))
				break;
			else
				ldriver.findElement(By.xpath("//div[@class='k-header']/a[3]")).click();
			Thread.sleep(2000);
			List<WebElement> dates = ldriver.findElements(By.xpath("//table[@class='k-content k-month']//td"));
			for (WebElement ele : dates) {
				String dt = ele.getText();
				if (dt.equals(date)) {
					ele.click();
					break;
				}
			}
		}
		ScreenShots.captureScreenshot(ldriver, "DateBirth");
	}

	public void companyName(String company) {
		helper.waitForElement(companyName, 10);
		companyName.sendKeys(company);
		ScreenShots.captureScreenshot(ldriver, "companyName");
	}

	public void tax() {
		helper.waitForElement(tax, 10);
		tax.click();
		ScreenShots.captureScreenshot(ldriver, "tax");
	}
	
	public void letter(String store) {
		letter.click();
		WebElement item;
		if (store.equals("Test store 2")) {
			item = testStore;
		}else {
			item = testStore;
		}
//		newItem.click();
		JavascriptExecutor js = (JavascriptExecutor) ldriver;
		js.executeScript("arguments[0].click();", item);
		ScreenShots.captureScreenshot(ldriver, "letter");
	}
	
	public void setCustomerRoles(String role) {
		if (!role.equals("Vendors")) {
			ldriver.findElement(By.xpath("//*[@id='SelectedCustomerRoleIds_taglist']/li/span[@title='delete']")).click();
		}
		customerRole.click();
		WebElement listitem;
		if (role.equals("Administrators")) {
			listitem = lstitemAdministartor;
		} else if (role.equals("Forum Moderators")) {
			listitem = lstitemForum;
		} else if (role.equals("Guests")) {
			listitem = lstitemGuests;
		} else if (role.equals("Registered")) {
			listitem = lstitemRegistered;
		} else if (role.equals("Vendors")) {
			listitem = lstitemVendor;
		} else {
			listitem = lstitemGuests;
		}
//		listitem.click();
		JavascriptExecutor js = (JavascriptExecutor) ldriver;
		js.executeScript("arguments[0].click();", listitem);
		ScreenShots.captureScreenshot(ldriver, "setCustomerRoles");
	}

	public void setVendor(String value) {
		Select drp = new Select(vendorId);
		drp.selectByVisibleText(value);
		ScreenShots.captureScreenshot(ldriver, "setVendor");
	}

	public void setActive() {
		helper.waitForElement(active, 10);
		Boolean status = active.isSelected();
		if (status == true) {
			System.out.println("Checkbox is Selected");
		} else {
			active.click();
		}
		ScreenShots.captureScreenshot(ldriver, "setActive");
	}

	public void setAdmin(String admin) {
		helper.waitForElement(adminComment, 10);
		adminComment.sendKeys(admin);
		ScreenShots.captureScreenshot(ldriver, "setAdmin");
	}

	public void save() {
		save.click();
	}
}
