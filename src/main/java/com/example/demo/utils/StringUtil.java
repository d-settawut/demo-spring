package com.example.demo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public int countWords(String plainText, String word) {
        if (isNullOrEmpty(plainText) || isNullOrEmpty(word)) return 0;

        Pattern pattern = Pattern.compile("\\b" + word + "\\b");
        Matcher matcher = pattern.matcher(plainText);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}
