package net.gfu.seminar.spring.helloworld.web.jsf;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class AddGuestIntegrationTest {
	private WebDriver driver;
	
	@Before public void setup() {
		driver = new HtmlUnitDriver();
		// And now use this to visit the welcome page
		driver.get("http://localhost:8080/helloWorldSpringJSF01/add.jsf");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Alternatively the same thing can be done like this
		// driver.navigate().to("http://localhost:8080/helloWorldSpringJSF01/");
	}

	@Test
	public  void testAddGuestWithValidValues() {
        // Find the text input element by its name
        WebElement firstnameInputField = driver.findElement(By.id("addGuestForm:firstname"));
        firstnameInputField.sendKeys("Hans");
        
        WebElement lastnameInputField = driver.findElement(By.id("addGuestForm:lastname"));
        lastnameInputField.sendKeys("Fall");

        // Now submit the form. WebDriver will find the form for us from the element
        firstnameInputField.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());
        
        // Should see page: HelloWorld page
        assertThat(driver.findElement(By.id("welcome")).getText(), allOf(containsString("Hans"), containsString("Fall")));
	}
	
	@After public void tearDown() {
        //Close the browser
        driver.quit();
    }
}
