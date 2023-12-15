package com.utez.geco.utils;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomService {
    private HashMap<String, Object> response;

    private String[] blacklist = {";", "@@",
            "SELECT", "select", "script>", "<script", "UPDATE",
            "update", "DELETE", "delete", "input", "button",
            "div", "html", "char", "varchar", "nvarchar", "hooks.js",
            "int", "integer", "String", "sys", "sysobjects",
            "sysobject",".js"};

    private String[] blacklist2 = {"@@", "SELECT", "select", "script>", "<script", "UPDATE",
            "update", "DELETE", "delete", "input", "button",
            "div", "html", "char", "varchar", "nvarchar", "hooks.js",
            "int", "integer", "String", "sys", "sysobjects",
            "sysobject",".js"};

    public HashMap<String, Object> createCustomResponse(String message, Object data) {
        response = new HashMap<>();
        response.put("message", message);
        if(data != null) {
            response.put("data", data);
        }

        return response;
    }

    public boolean checkBlacklists(String data) {
        boolean flag = false;
        Pattern regex;
        for(String word: blacklist) {
            regex = Pattern.compile("\\b" + word + "\\b");
            Matcher matcher = regex.matcher(data);
            if(matcher.find()) {
                System.out.println("Se encontró esta palabra maliciosa: " + word);
                flag = true;
            }
        }

        for(String word: blacklist2) {
            regex = Pattern.compile("\\b" + word + "\\b");
            Matcher matcher = regex.matcher(data);
            if(matcher.find()) {
                System.out.println("Se encontró esta palabra maliciosa: " + word);
                flag = true;
            }
        }

        return flag;
    }
}
