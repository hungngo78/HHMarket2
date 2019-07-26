package com.hhmarket.mobile.search;

import java.util.ArrayList;
import java.util.List;

public class StoresData {
    private static List<String> stores ;
    static {
        stores =  new ArrayList<String>();
        stores.add("dress");
        stores.add("women");
        stores.add("men");
        stores.add("sweater");
        stores.add("short");
        stores.add("skirt");
        stores.add("sexy");
        stores.add("stretch");
        stores.add("wear-to-work");
        stores.add("cocktail & party");
        stores.add("night out");
        stores.add("activewear");
    }

    public static List<String> getStores(){
        return stores;
    }

    public static List<String> filterData(String searchString){
        List<String> searchResults =  new ArrayList<String>();
        if(searchString != null){
            searchString = searchString.toLowerCase();

            for(String rec :  stores){
                if(rec.toLowerCase().contains(searchString)){
                    searchResults.add(rec);
                }
            }
        }
        return searchResults;
    }
}