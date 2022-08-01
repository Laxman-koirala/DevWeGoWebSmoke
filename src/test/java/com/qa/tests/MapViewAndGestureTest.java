package com.qa.tests;

import com.microsoft.playwright.Page;
import com.qa.factoryinitilizer.PlayWrightFactory;
import com.qa.functionality.CookiesPage;
import com.qa.functionality.MapViewAndGesture;
import com.qa.functionality.Search;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapViewAndGestureTest {

    PlayWrightFactory pwf;
    Page page;
    CookiesPage cookiesPage;
    MapViewAndGesture mapViewAndGesture;

    @BeforeTest
    public void setup() throws InterruptedException {
        pwf = new PlayWrightFactory();
        pwf.logInit.info("Session Started for Search");
        page = pwf.initBrowser("chrome","yes");
        cookiesPage = new CookiesPage(page);
        mapViewAndGesture = new MapViewAndGesture(page);
        pwf.logInit.info("Browser: Chrome");
        pwf.logInit.info("allowing Location permission");
        page.navigate("https://dev.hereweb.here.com");
        pwf.logInit.info(String.format("Navigating to webpage :%s",page.url()));
        cookiesPage.acceptCookies();
        cookiesPage.cookiesLog.info("Cookies accepted");

    }

    @Test(priority = 0)
    public void zoomInTest(){
        String [] beforeZoomSpliting = page.url().split(",",4);
        mapViewAndGesture.zoomIn();
        String [] afterZoomSpliting = page.url().split(",",4);
        int beforeSplitZL = Integer.parseInt(beforeZoomSpliting[2]);
        int afterSplitZL = Integer.parseInt(afterZoomSpliting[2]);
        int subtractAfterZoomIn = afterSplitZL-10;

        if (beforeSplitZL==subtractAfterZoomIn) {
            mapViewAndGesture.gestureLog.info("Zoom-in functionality passed");

        }


    }

    @Test(priority = 1)
    public void ZoomOutTest(){
        String [] beforeZoomSpliting = page.url().split(",",4);
        mapViewAndGesture.zoomOut();
        String [] afterZoomSpliting = page.url().split(",",4);
        int beforeSplitZL = Integer.parseInt(beforeZoomSpliting[2]);
        int afterSplitZL = Integer.parseInt(afterZoomSpliting[2]);
        int addAfterZoomOut = afterSplitZL+10;
        if (beforeSplitZL==addAfterZoomOut) {
            mapViewAndGesture.gestureLog.info("Zoom-out functionality passed");

        }

    }

    @AfterTest
    public void tearDown(){
        page.context().browser().close();
    }

}



