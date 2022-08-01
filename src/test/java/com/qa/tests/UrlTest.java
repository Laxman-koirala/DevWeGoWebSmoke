package com.qa.tests;

import com.microsoft.playwright.Page;
import com.qa.factoryinitilizer.PlayWrightFactory;
import com.qa.functionality.CookiesPage;
import com.qa.functionality.Search;
import com.qa.functionality.Url;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class UrlTest {


    PlayWrightFactory pwf;
    Page page;
    Url url;
    CookiesPage cookiesPage;

    @BeforeTest
    public void setup() throws InterruptedException {
        pwf = new PlayWrightFactory();
        pwf.logInit.info("Session Started for Cookies");
        page = pwf.initBrowser("chrome","yes");
        url = new Url(page);
        cookiesPage = new CookiesPage(page);
        pwf.logInit.info("Browser: Chrome");
        pwf.logInit.info("allowing Location permission");
        page.navigate("https://dev.hereweb.here.com");
        pwf.logInit.info(String.format("Navigating to webpage :%s",page.url()));
        cookiesPage.acceptCookies();
        cookiesPage.cookiesLog.info("Cookies accepted");

    }

    @Test(priority=0)
    public void CheckZLinURL(){
        url.urlLog.info("Checking multiple url with provided zoom level");
        ArrayList<String> urls= new ArrayList<>();
        urls= url.listOfUrl();
        urls.stream().forEach(e-> {
            page.navigate(e);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                url.urlLog.error("run time exception");
            }
            Assert.assertEquals(page.url(), e);
        });
        url.urlLog.info("Checking multiple url with different zoom level: Passed");

    }


    @AfterTest
    public void tearDown(){
        page.context().browser().close();
    }
}
