package Utilities;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenShots {
	public static void captureScreenshot(WebDriver ldriver,String ScreenshotName) {
		try {
			TakesScreenshot ts = (TakesScreenshot) ldriver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			FileHandler.copy(src, new File("./ScreenShots/"+System.currentTimeMillis()+ScreenshotName+".png"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception while taking screenshot"+e.getMessage());
		}

	}
}
