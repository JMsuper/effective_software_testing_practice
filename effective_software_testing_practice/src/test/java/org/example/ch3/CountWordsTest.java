package org.example.ch3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountWordsTest {

    @Test
    void twoWordsEndingWithS(){
        int words = new CountWords().count("dogs cats");
        assertEquals(2, words);
    }

    @Test
    void twoWordsEndingWithR(){
        int words = new CountWords().count("dogr catr");
        assertEquals(2, words);
    }

    @Test
    void noWordsAll(){
        int words = new CountWords().count("dog cat");
        assertEquals(0, words);
    }
}