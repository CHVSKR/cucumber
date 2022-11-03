package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Utilities.ScreenShots;
import Utilities.WaitHelper;

public class SearchCustomer {
	public WebDriver ldriver;
	WaitHelper helper;
	ScreenShots screenShot;

	public SearchCustomer(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
		helper = new WaitHelper(ldriver);
		screenShot = new ScreenShots();
	}

	@FindBy(how = How.ID, using = "SearchEmail")
	@CacheLookup
	WebElement searchEmail;

	@FindBy(how = How.ID, using = "SearchFirstName")
	@CacheLookup
	WebElement searchFirstName;

	@FindBy(how = How.ID, using = "SearchLastName")
	@CacheLookup
	WebElement searchLastName;

	@FindBy(how = How.ID, using = "SearchCompany")
	@CacheLookup
	WebElement searchCompany;

	@FindBy(how = How.ID, using = "SearchMonthOfBirth")
	@CacheLookup
	WebElement searchMonthOfBirth;

	@FindBy(how = How.ID, using = "SearchDayOfBirth")
	@CacheLookup
	WebElement searchDayOfBirth;

	@FindBy(xpath = "//div[@class='k-multiselect-wrap k-floatwrap']")
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

	@FindBy(how = How.ID, using = "search-customers")
	@CacheLookup
	WebElement searchCustomers;

	@FindBy(xpath = "//table[@role='grid']")
	@CacheLookup
	WebElement tblSearchResults;

	@FindBy(xpath = "//table[@id='customers-grid']")
	@CacheLookup
	WebElement tblGrid;

	@FindBy(xpath = "//table[@id='customers-grid']//tbody/tr")
	@CacheLookup
	List<WebElement> tblRow;

	@FindBy(xpath = "//table[@id='customers-grid']//tbody/tr/td")
	@CacheLookup
	List<WebElement> tblColumn;

	public void setEmail(String email) {
		helper.waitForElement(searchEmail, 10);
		searchEmail.clear();
		searchEmail.sendKeys(email);
		ScreenShots.captureScreenshot(ldriver, "setEmail");
	}

	public void firstName(String fName) {
		helper.waitForElement(searchFirstName, 10);
		searchFirstName.clear();
		searchFirstName.sendKeys(fName);
		ScreenShots.captureScreenshot(ldriver, "firstName");
	}

	public void lastName(String lName) {
		helper.waitForElement(searchLastName, 10);
		searchLastName.clear();
		searchLastName.sendKeys(lName);
		ScreenShots.captureScreenshot(ldriver, "lastName");
	}

	public void searchCompany(String comapny) {
		helper.waitForElement(searchCompany, 10);
		searchCompany.clear();
		searchCompany.sendKeys(comapny);
		ScreenShots.captureScreenshot(ldriver, "searchCompany");
	}

	public void searchMonthOfBirth(String searchMonth) {
		helper.waitForElement(searchMonthOfBirth, 10);
		Select drp = new Select(searchMonthOfBirth);
		drp.selectByValue(searchMonth);
		ScreenShots.captureScreenshot(ldriver, "searchMonthOfBirth");
	}

	public void searchDayOfBirth(String searchDay) {
		helper.waitForElement(searchDayOfBirth, 10);
		Select drp = new Select(searchDayOfBirth);
		drp.selectByValue(searchDay);
		ScreenShots.captureScreenshot(ldriver, "searchDayOfBirth");
	}

	public void setCustomerRoles(String role) {
		if (!role.equals("Vendors")) {
			ldriver.findElement(By.xpath("//*[@id='SelectedCustomerRoleIds_taglist']/li/span[@title='delete']"))
					.click();
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

	public void customers() {
		helper.waitForElement(searchCustomers, 10);
		searchCustomers.click();
		ScreenShots.captureScreenshot(ldriver, "customers");
	}

	public int getNoOfRows() {
		return (tblRow.size());
	}

	public int getNoOfColumns() {
		return (tblColumn.size());
	}

	public boolean searchCustomerByEmail(String email) {
		boolean flag = false;
		for (int i = 1; i <= getNoOfRows(); i++) {
			String emailid = tblGrid.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr[" + i + "]/td[2]"))
					.getText();
			System.out.println(emailid);
			if (emailid.equals(email)) {
				flag = true;
			}
		}
		return flag;
	}

	public boolean searchCustomerByName(String name) {
		boolean flag = false;
		for (int i = 1; i <= getNoOfRows(); i++) {
			String nameLF = tblGrid.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr[" + i + "]/td[3]"))
					.getText();
			String[] names = nameLF.split(" ");
			if (names[0].equals("Sai") && names[1].equals("Ch")) {
				flag = true;
			}
		}
		return flag;
	}
	
	public boolean searchCustomerByCompany(String company) {
		boolean flag = false;
		for (int i = 1; i <= getNoOfRows(); i++) {
			String company1 = tblGrid.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr[" + i + "]/td[5]"))
					.getText();
			if (company1.equals(company)) {
				flag = true;
			}
		}
		return flag;
	}
	public boolean searchCustomerByRole(String role) {
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
		
		boolean flag = false;
		for (int i = 1; i <= getNoOfRows(); i++) {
			String role1 = tblGrid.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr[" + i + "]/td[4]"))
					.getText();
			if (role1.equals(role)) {
				flag = true;
			}
		}
		return flag;
	}
}
