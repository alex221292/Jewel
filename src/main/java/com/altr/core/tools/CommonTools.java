package com.altr.core.tools;


public class CommonTools {

    public static String getSafeString(String input) {
        if(input == null){
            return "";
        } else {
            return input;
        }
    }

}
