package com.surfm.baikalminer;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MinerSetuper {

	@Value("${minerPass}")
	private String minerPass;
	@Value("#{'${host}'.split(',')}")
	private String[] hosts;

	private WebDriver driver = null;
	private DesiredCapabilities capabilities = DesiredCapabilities.chrome();

	@PostConstruct
	void init() {
		capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));
		System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
	}

	public void setup() {
		for (String h : hosts) {
			login(h);
		}
	}

	public void login(String host) {

		driver = new ChromeDriver(capabilities);

		WebDriverWait wait = new WebDriverWait(driver, 5);
		driver.get("http://" + host + "/login.php");
		System.out.println("##start login ");
		// Login
		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver d) {

				EventFiringWebDriver wd = new EventFiringWebDriver(d);
				// create an object of class WebDriverListener and pass the
				// driver instance
				wd.register(new AbstractWebDriverEventListener() {
					@Override
					public void afterNavigateTo(String url, WebDriver driver) {
						System.out.println("afterNavigateTo=" + url);
					}

					@Override
					public void beforeClickOn(WebElement element, WebDriver driver) {
						System.out.println("beforeClickOn=" + element);
					}

					@Override
					public void afterNavigateForward(WebDriver driver) {
						System.out.println("afterNavigateForward=" + driver);
					}

					@Override
					public void afterClickOn(WebElement element, WebDriver driver) {
						System.out.println("afterClickOn=" + driver);
					}
				});

				System.out.println("## login User ");
				WebElement element = wd.findElement(By.id("userPassword"));
				element.sendKeys(minerPass);
				element = wd.findElement(By.id("loginbutton"));
				element.click();

				WebDriverWait w = new WebDriverWait(d, 5);
				w.until(new ExpectedCondition<Boolean>() {
					@Override
					public Boolean apply(WebDriver d) {
						d.findElement(By.xpath("//a[@href='#/miner']")).click();
						WebDriverWait w = new WebDriverWait(d, 5);
						w.until(new ExpectedCondition<Boolean>() {
							@Override
							public Boolean apply(WebDriver d) {
								setupConfig(d);
								return true;
							}

						});

						return true;
					}
				});

				// firingDriver.quit();
				return true;
			}
		});

	}

	private void setupConfig(WebDriver d) {
		System.out.println("setupConfig~");
		WebElement element =  d.findElement(By.cssSelector("[ng-model*='pool.url']"));
		element.clear();
		element.sendKeys("xxx");

	}

}