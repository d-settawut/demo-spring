package com.example.demo.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilTest {
    private final StringUtil stringUtil = new StringUtil();

    @Test
    public void countWordsCaseFound() {
        String text = "This is a pen, ThIs is a apple, This is a book, THIS is a test";
        String word = "This";

        int actual = stringUtil.countWords(text, word);
        assertEquals(2, actual);
    }

    @Test
    public void countWordsCaseNull() {
        int actual = stringUtil.countWords(null, null);
        assertEquals(0, actual);
    }

    @Test
    public void countWordsCaseEmptyString() {
        int actual = stringUtil.countWords("", "");
        assertEquals(0, actual);
    }

    @Test
    void countWordsCaseNotFound() {
        String text = "This is a book";
        String word = "boo";

        int actual = stringUtil.countWords(text, word);
        assertEquals(0, actual);
    }
}