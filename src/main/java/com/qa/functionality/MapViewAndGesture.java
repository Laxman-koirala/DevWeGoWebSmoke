package com.qa.functionality;
import com.microsoft.playwright.Page;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
public class MapViewAndGesture {
    public static Logger gestureLog = LogManager.getLogger(MapViewAndGesture.class.getName());
    private String plusZoom = "#Button >> nth=2";
    private String minusZoom = "#Button >> nth=3";
    private Page page;

    public MapViewAndGesture(Page page){
        this.page=page;
    }

    public void zoomIn(){
        page.click(plusZoom);
    }

    public void zoomOut(){
        page.click(minusZoom);
    }

}
/*
page.locator("#Button").nth(2).click();
        assertThat(page).hasURL("https://dev.hereweb.here.com/?map=52.5,13.4,11,omv");
        // Click #Button >> nth=3
        page.locator("#Button").nth(3).click();
        assertThat(page).hasURL("https://dev.hereweb.here.com/?map=52.5,13.4,10,omv");
*/