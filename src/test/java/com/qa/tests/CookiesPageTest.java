package com.qa.tests;

import com.microsoft.playwright.Page;
import com.qa.factoryinitilizer.PlayWrightFactory;
import com.qa.functionality.CookiesPage;
import com.qa.functionality.Search;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CookiesPageTest {
    PlayWrightFactory pwf;
    Page page;
    CookiesPage cookiesPage;
    Search search;

    @BeforeTest
    public void setup() throws InterruptedException {
        pwf = new PlayWrightFactory();
        pwf.logInit.info("Session Started for Cookies");
        page = pwf.initBrowser("chrome","yes");
        search = new Search(page);
        cookiesPage = new CookiesPage(page);
        pwf.logInit.info("Browser: Chrome");
        pwf.logInit.info("allowing Location permission");
        page.navigate("https://dev.hereweb.here.com");
        pwf.logInit.info(String.format("Navigating to webpage :%s",page.url()));
    }


    @Test(priority = 3)
    public void acceptCookiesTest(){
        cookiesPage.acceptCookies();
        search.searchItem("Viru keskus");
        search.latestHistory();
        String searchname= search.getLatestHistory();

        if (searchname.contains("Viru")){
            cookiesPage.cookiesLog.info("Cookies accepted and verified");
        }else{
            cookiesPage.cookiesLog.error("Cookies accepted but unverified");
        }
    }

    /*
    @Test(priority = 2)
    public void blockCookiesTest(){
        cookiesPage.blockCookies();
        search.searchItem("Viru keskus");
        try {
            search.latestHistory();
            search.getLatestHistory();
            cookiesPage.cookiesLog.error("Cookies accepted, when user block it");
        } catch (Exception e){
            cookiesPage.cookiesLog.info("Cookies blocked, hence not getting history");
        }


    }
     */

    @Test(priority = 2)
    public void hereImprovementLinkTest(){
        cookiesPage.hereImprovementLink();
        cookiesPage.cookiesLog.info("improvement link clicked");
        assertThat(page).hasURL("https://legal.here.com/en-gb/privacy/cookie-policy");
        cookiesPage.cookiesLog.info("Headed to here improvement page");
        page.goBack();

    }

    @Test(priority = 0)
    public void cookiesHeaderTest(){

        String header= cookiesPage.getCookiesHeader();
        Assert.assertEquals("Improving services",header);

    }

    @Test(priority = 1)
    public void cookiesMessageTest(){
        String message = cookiesPage.getCookiesMessage();
        String actual = "HERE uses cookies from our websites to bring you services" +
                " and info that matter more to you, including personalization and" +
                " improvements to our websites. By using this website, you agree" +
                " to the use of cookies based on your choices.  ";
        Assert.assertEquals(actual, message);

    }

    @AfterTest
    public void tearDown(){
        page.context().browser().close();
    }
}
