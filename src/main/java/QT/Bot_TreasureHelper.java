package QT;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yaml.snakeyaml.Yaml;

public class Bot_TreasureHelper {
	
	public void ScrollIntoView(WebDriver driver , WebElement element)
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
	}
	
	public void ExplicitWait(WebDriver driver,  WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void Wait (int sec) throws InterruptedException
	{
		Thread.sleep(sec*1000);
	}
	


}
