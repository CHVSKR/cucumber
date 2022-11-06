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

import Utilities.ScreenShots;
import Utilities.WaitHelper;

public class DynamicTable {
	public WebDriver ldriver;
	WaitHelper helper;
	ScreenShots screenShot;

	public DynamicTable(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
		helper = new WaitHelper(ldriver);
		screenShot = new ScreenShots();
	}

	@FindBy(how = How.ID, using = "input-username")
	@CacheLookup
	WebElement uname;

	@FindBy(how = How.ID, using = "input-password")
	@CacheLookup
	WebElement password;

	@FindBy(xpath = "//button[@type='submit']")
	@CacheLookup
	WebElement login;

	@FindBy(xpath = "//a[@href='#collapse-4']")
	@CacheLookup
	WebElement sales;

	@FindBy(xpath = "//*[@id=\"collapse-4\"]/li[3]/a")
	@CacheLookup
	WebElement returns;

	@FindBy(xpath = "//div[@class='col-sm-6 text-end']")
	@CacheLookup
	WebElement pageCount;

	public void setUserName(String username) {
		uname.clear();
		uname.sendKeys(username);
		ScreenShots.captureScreenshot(ldriver, "uname");
	}

	public void setpswd(String pswd) {
		password.clear();
		password.sendKeys(pswd);
		ScreenShots.captureScreenshot(ldriver, "password");
	}

	public void loginClick() {
		login.click();
		ScreenShots.captureScreenshot(ldriver, "login");
	}

	public String getPageTitle() {
		return ldriver.getTitle();
	}

	public void saleIcon() {
		helper.waitForElement(sales, 10);
		sales.click();
		ScreenShots.captureScreenshot(ldriver, "saleIcon");

	}

	public void returnMenu() throws InterruptedException {
		helper.waitForElement(returns, 10);
		returns.click();
		ScreenShots.captureScreenshot(ldriver, "returnMenu");
		String text = pageCount.getText();
		int totalPages = Integer.valueOf(text.substring(text.indexOf("(") + 1, text.indexOf("Pages") - 1));
		System.out.println("Number of Pages" + totalPages);
		
		for (int p = 1; p <= totalPages; p++) {
			WebElement activePage = ldriver.findElement(By.xpath("//ul[@class='pagination']//li//span"));
			System.out.println("Active Page:" + activePage.getText());
			JavascriptExecutor js = (JavascriptExecutor) ldriver;
			js.executeScript("arguments[0].scrollIntoView();", activePage);
			Thread.sleep(2000);
			activePage.click();
			int rows = ldriver.findElements(By.xpath("//table[@class='table table-bordered table-hover']//tbody/tr"))
					.size();
			System.out.println("Number of Rows" + rows);

			for (int r = 1; r <= rows; r++) {
				int columns = ldriver.findElements(By.xpath("//table[@class='table table-bordered table-hover']//tbody/tr["+r+"]/td")).size();
				System.out.println("Number of columns" +columns);
				for (int c = 1; c <= columns; c++) {
					List<WebElement> gridData = ldriver.findElements(By.xpath(
							"//table[@class='table table-bordered table-hover']//tbody/tr[" + r + "]/td[" + c + "]"));
					for(WebElement colData : gridData) {
					String Data = colData.getText();
					System.out.println("Column Data" +Data);
					}
				}
			}
			if(p<totalPages) {
			String pageno = Integer.toString(p+1);
			ldriver.findElement(By.xpath("//ul[@class='pagination']//li//a[text()='" + pageno + "']")).click();
			Thread.sleep(3000);
			}
		}
	}
}
