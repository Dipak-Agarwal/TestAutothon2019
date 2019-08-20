package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.MasterClass;

public class OrangeAppLoginPage extends MasterClass{

	public By usernameTextBox=By.xpath("//input[@id='txtUsername']");
	public By passwordTextBox=By.xpath("//input[@id='txtPassword']");
	public By button_login=By.xpath("//input[@value='LOGIN'][@type='submit']");
	public By text_invalidCreds=By.xpath("//span[text()='Invalid credentials']");	
}

