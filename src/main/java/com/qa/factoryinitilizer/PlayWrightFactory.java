package com.qa.factoryinitilizer;
import com.microsoft.playwright.*;

import java.util.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class PlayWrightFactory {

    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    public static Logger logInit = LogManager.getLogger(PlayWrightFactory.class.getName());

    public Page initBrowser(String browserName, String allowLocationPermission){
        playwright = Playwright.create();
        Map<String, Object> map = new HashMap<>();
        /*
        map.put("chromium", playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
        map.put("firefox", playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
        map.put("safari", playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
        */
        map.put("chrome", playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)));

        String key = browserName.toLowerCase();
        String LocationPermission = allowLocationPermission.toLowerCase();
        if (map.containsKey(key)){
            browser = (Browser) map.get(key);
            browserContext = browser.newContext();
            if (LocationPermission=="yes"){
                browserContext.grantPermissions(Arrays.asList("geolocation"));
            }
            //browserContext.clearPermissions();
            page = browserContext.newPage();
        }
        return page;

    }

}
