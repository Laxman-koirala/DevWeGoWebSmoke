package com.qa.functionality;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.microsoft.playwright.Page;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
public class Url {
    public static Logger urlLog = LogManager.getLogger(Url.class.getName());
    private Page page;

    public Url(Page page){
        this.page =page;
    }

    public static ArrayList<String> listOfUrl(){

        String [] ZLs= {"9", "16", "22", "14.6", "17.8", "8.88", "10.14"};
        ArrayList<String> urls= (ArrayList<String>) Arrays.stream(ZLs).map(e -> e = String.format("https://dev.hereweb.here.com/?map=59.42016,24.80431,%s,omv", e))
                .collect(Collectors.toList());

        return urls;
    }





}
