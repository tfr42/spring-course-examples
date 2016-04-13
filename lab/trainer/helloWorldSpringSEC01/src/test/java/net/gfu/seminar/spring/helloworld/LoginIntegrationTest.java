package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class LoginIntegrationTest {
	private WebDriver driver;
	
	@Before public void setup() {
		driver = new HtmlUnitDriver();
		// And now use this to visit the welcome page
		driver.get("http://localhost:8080/helloWorldSpringSEC01/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Alternatively the same thing can be done like this
		// driver.navigate().to("http://localhost:8080/helloWorldSpringSEC01/");
	}

	@Test
	public  void testSuccesfulLogin() {
        // Find the text input element by its name
        WebElement usernameInputField = driver.findElement(By.name("j_username"));
        // Enter username
        usernameInputField.sendKeys("efall");
        
        WebElement passwordInputField = driver.findElement(By.name("j_password"));
        // Enter username
        passwordInputField.sendKeys("pass");

        // Now submit the form. WebDriver will find the form for us from the element
        usernameInputField.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());
        
        // Should see page: HelloWorld page
        assertEquals("SEC01" , driver.getTitle());
	}
	
	@Test
	public  void testFailedLogin() {
        // Find the text input element by its name
        WebElement usernameInputField = driver.findElement(By.name("j_username"));
        // Enter username
        usernameInputField.sendKeys("foo");
        
        WebElement passwordInputField = driver.findElement(By.name("j_password"));
        // Enter username
        passwordInputField.sendKeys("bar");

        // Now submit the form. WebDriver will find the form for us from the element
        usernameInputField.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());
        
        // Should see: Login page
        assertEquals("Login" , driver.getTitle());
	}
	
	@After public void tearDown() {
        //Close the browser
        driver.quit();
    }
}
