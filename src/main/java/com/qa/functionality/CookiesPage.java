package com.qa.functionality;

import com.microsoft.playwright.Page;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class CookiesPage {
    public static Logger cookiesLog = LogManager.getLogger(CookiesPage.class.getName());

    private Page page;

    private String acceptCookiesButton = "text=Accept cookies >> #Button";
    private String blockCookiesButton = "text=Decline >> #Button";
    private String cookiesHeader= ".CookiePrompt__header-title";
    private String cookiesMessage = ".CookiePrompt__message";
    private String cookiesPolicyLink = ".CookiePrompt__link";

    public CookiesPage(Page page){
        this.page=page;
    }


    public void acceptCookies(){
        page.click(acceptCookiesButton);
    }

    public void blockCookies(){
        page.click(blockCookiesButton);

    }

    public void hereImprovementLink(){
        page.click(cookiesPolicyLink);

    }

    public String getCookiesMessage(){
        return page.textContent(cookiesMessage);

    }

    public String getCookiesHeader(){
        return page.textContent(cookiesHeader);
    }
}
