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

				System.out.println("## login User ");
				WebElement element = d.findElement(By.id("userPassword"));
				element.sendKeys(minerPass);
				element = d.findElement(By.id("loginbutton"));
				element.click();

				WebDriverWait w = new WebDriverWait(d, 7);
				w.until(new ExpectedCondition<Boolean>() {
					@Override
					public Boolean apply(WebDriver d) {
						System.out.println("apply=" + d.getCurrentUrl());
						 d.findElement(By.xpath("//a[@href='#/miner']")).click();

						setupConfig(d);
						// w.until(new ExpectedCondition<Boolean>() {
						// @Override
						// public Boolean apply(WebDriver d) {
						// System.out.println("apply="+d.getCurrentUrl());
						// setupConfig(d);
						// return true;
						// }
						// });

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
		WebElement element = d.findElement(By.cssSelector("[ng-model*='pool.url']"));
		// element.clear();
		// element.sendKeys("xxx");

	}

}