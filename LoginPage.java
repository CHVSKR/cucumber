package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public WebDriver ldriver;
	
	public LoginPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver,this);
	}
@FindBy(id="Email")
@CacheLookup
WebElement usrname;
@FindBy(id="Password")
@CacheLookup
WebElement pswd;
@FindBy(xpath="//button[text()='Log in']")
@CacheLookup
WebElement loginclick;
@FindBy(xpath="//a[text()='Logout']")
@CacheLookup
WebElement logoutclick;

public void setUserName(String uname) {
	usrname.clear();
	usrname.sendKeys(uname);
}
public void setpswd(String pwd) {
	pswd.clear();
	pswd.sendKeys(pwd);
}
public void loginClick() {
	loginclick.click();
}
public void logoutClick() {
	logoutclick.click();
}
}

