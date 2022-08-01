package com.qa.functionality;

import com.microsoft.playwright.Page;

import java.util.ArrayList;

import com.qa.factoryinitilizer.PlayWrightFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
public class Search {
    public static Logger searchlog = LogManager.getLogger(Search.class.getName());
    private Page page;
    private String searchBox = "input[type='text']";
    private String latestSearch = "ul.SearchResultList>li:first-of-type";
    private String clearIcon = "hds-hero-search hds-icon";

    public Search(Page page){
        this.page = page;
    }

    public void searchItem(String userSearch){
        page.click(searchBox);
        page.fill(searchBox, userSearch);
        page.press(searchBox, "Enter");
        page.click(clearIcon);
    }
    public void latestHistory(){
        page.click(searchBox);
    }

    public String getLatestHistory(){
        return page.textContent(latestSearch);
    }

}


