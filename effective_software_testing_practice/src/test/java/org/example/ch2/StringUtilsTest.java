package org.example.ch2;


import static org.apache.commons.lang3.StringUtils.substringsBetween;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class StringUtilsTest {

    /*
        T1 : str이 null인 경우
        T2 : str이 빈 문자열인 경우
        T3 : open이 null인 경우
        T4 : open이 빈 문자열인 경우
        T5 : close이 null인 경우
        T6 : close이 빈 문자열인 경우

        다음으로 str의 길이가 1인 경우
        T7 : str에 문자가 하나밖에 없고 open 태그와 일치하는 경우
        T8 : str에 문자가 하나밖에 없고 close 태그와 일치하는 경우
        T9 : str에 문자가 하나밖에 없고 open, close 태그와 일치하지 않는 경우
        T10 : str에 문자가 하나밖에 없고 open, close 태그가 같은 경우

        이제 str의 길이 > 1, open의 길이 = 1, close의 길이 = 1인 경우
        T11 : str에 문자가 두개 이상이고 open태그와 close태그를 모두 포함하지 않는 경우
        T12 : str에 문자가 두개 이상이고 open 태그만 포함하고 close 태그는 포함하지 않는 경우
        T13 : str에 문자가 두개 이상이고 close 태그만 포함하고 open 태그는 포함하지 않는 경우
        T14 : str에 문자가 두개 이상이고 open 태그와 close 태그를 모두 포함하는 경우
        T15 : str에 문자가 두개 이상이고 open 태그와 close 태그를 여러번 포함하는 경우

        다음으로 str의 길이 > 1, open의 길이 > 1, close의 길이 > 1인 경우
        T16 : str이 open 태그와 close 태그를 모두 포함하지 않는 경우
        T17 : str이 open 태그만 포함하고 close 태그는 포함하지 않는 경우
        T18 : str이 close 태그만 포함하고 open 태그는 포함하지 않는 경우
        T19 : str이 open 태그와 close 태그를 모두 포함하는 경우
        T20 : str이 open 태그와 close 태그를 여러번 포함하는 경우

        마지막으로 경계를 위한 테스트
        T21 : str이 open 태그와 close 태그를 모두 포함하지만, 그 사이에 문자가 없는 경우
     */

    @Test
    void strIsNullOrEmpty(){
        assertEquals(null,substringsBetween(null,"a","b")); // T1
        assertTrue(substringsBetween("","a","b").length == 0); // T2
    }

    @Test
    void openIsNullOrEmpty(){
        assertEquals(null,substringsBetween("a",null,"b")); // T3
        assertEquals(null,substringsBetween("a","","b")); // T4
    }

    @Test
    void closeIsNullOrEmpty(){
        assertEquals(null,substringsBetween("a","b",null)); // T5
        assertEquals(null,substringsBetween("a","b","")); // T6
    }

    @Test
    void strLengthIsOne(){
        assertEquals(null,substringsBetween("a","a","b")); // T7
        assertEquals(null,substringsBetween("a","b","a")); // T8
        assertEquals(null,substringsBetween("a","b","b")); // T9
        assertEquals(null,substringsBetween("a","a","a")); // T10
    }   

    @Test
    void strLengthIsMoreThanOne(){
        assertEquals(null,substringsBetween("abc","d","e")); // T11
        assertEquals(null,substringsBetween("abc","a","d")); // T12
        assertEquals(null,substringsBetween("abc","d","a")); // T13
        assertTrue(Arrays.equals(new String[]{"b"},substringsBetween("abc","a","c"))); // T14
        assertTrue(Arrays.equals(new String[]{"b","b"},substringsBetween("abcabc","a","c"))); // T15
    }

    @Test
    void openAndCloseLengthIsMoreThanOne(){
        assertEquals(null,substringsBetween("aabcc","xx","yy")); // T16
        assertEquals(null,substringsBetween("aabcc","aa","yy")); // T17
        assertEquals(null,substringsBetween("aabcc","yy","aa")); // T18
        assertTrue(Arrays.equals(new String[]{"b"},substringsBetween("aabcc","aa","cc"))); // T19
        assertTrue(Arrays.equals(new String[]{"b","b"},substringsBetween("aabccaabcc","aa","cc"))); // T20
    }

    @Test
    void boundary(){
        assertTrue(Arrays.equals(new String[]{""},substringsBetween("aacc","aa","cc"))); // T21
    }
}
