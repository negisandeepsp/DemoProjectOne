package library;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class SplashmathScreenshot 
{
	public WebDriver driver ;
	public SplashmathScreenshot(WebDriver driver)
	{
		this.driver = driver;
	}
	public String takeScreenshot(String FolderName , String imageName)
	{
		
		try 
		{
			TakesScreenshot photo=(TakesScreenshot)driver;
			File source = photo.getScreenshotAs(OutputType.FILE);
			
			String dest = "./ScreenShots/" + FolderName+ "/" + imageName + ".png";
			File destination = new File(dest);
			
			FileUtils.copyFile(source, destination);
			System.out.println(imageName + " ScreenShot Taken");
			
			return dest;
		} 
		
		catch (Exception e) 
		{
			System.out.println("Exception while taking Screenshot" + e.getMessage());
			return e.getMessage();
		}
		
		
	}
	

}