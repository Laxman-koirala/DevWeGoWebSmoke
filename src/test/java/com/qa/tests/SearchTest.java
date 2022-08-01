package com.qa.tests;

import com.microsoft.playwright.Page;
import com.qa.factoryinitilizer.PlayWrightFactory;
import com.qa.functionality.CookiesPage;
import com.qa.functionality.Search;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SearchTest {

    PlayWrightFactory pwf;
    Page page;
    Search search;
    CookiesPage cookiesPage;

    @BeforeTest
    public void setup(){
        pwf = new PlayWrightFactory();
        pwf.logInit.info("Session Started for Search");
        page = pwf.initBrowser("chrome","yes");
        search = new Search(page);
        cookiesPage = new CookiesPage(page);
        pwf.logInit.info("Browser: Chrome");
        pwf.logInit.info("allowing Location permission");
        page.navigate("https://dev.hereweb.here.com");
        pwf.logInit.info(String.format("Navigating to webpage :%s",page.url()));
        cookiesPage.acceptCookies();
        cookiesPage.cookiesLog.info("Cookies accepted");
    }

    @Test (priority=0)
    public void searchItemTest(){
        search.searchlog.info("Typing on search field");
        search.searchItem("Pizza");
        search.searchlog.info("Typing on search field: Passed");

    }

    @Test (priority=1)
    public void getLatestHistory(){
        search.searchlog.info("Getting latest search history");
        String [] names = {"pizza", "Ev charge", "Viru Keskus", "Lasnamae center"};
        ArrayList<String> userProvided = new ArrayList<>();
        Arrays.stream(names).forEach(e-> {
            search.searchItem(e);
            search.latestHistory();
            userProvided.add(search.getLatestHistory());
        });
        search.searchlog.info("Getting latest search history: Passed");
        if(userProvided.get(0).contains(names[0])){
            search.searchlog.info("Getting latest search history, 2nd TC: Passed");
        }
        //System.out.println(userProvided);

    }

    @AfterTest
    public void tearDown(){
        page.context().browser().close();
    }

}
