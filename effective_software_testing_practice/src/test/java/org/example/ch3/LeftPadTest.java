package org.example.ch3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.of;
import static org.junit.jupiter.api.Assertions.*;

class LeftPadTest {

    /*
        * 구획 도출
        - str 매개변수
            - 널인 경우
            - 빈 문자열인 경우
            - 빈 문자열이 아닌 경우
        - size 매개변수
            - 음수인 경우
            - 양수인 경우
        - padStr 매개변수
            - 널인 경우
            - 빈 문자열인 경우
            - 빈 문자열이 아닌 경우
        - str, size 매개변수
            - size < len(str)인 경우
            - size > len(str)인 경우
        * 경계
        - size가 0인 경우
        - str의 길이가 1인 경우
        - padStr의 길이가 1인 경우
        - size가 padStr의 길이와 같은 경우
     */

    /*
        T1 : str 이 null
        T2 : str 이 빈 문자열
        T3 : size 가 음수인 경우
        T4 : padStr 이 null
        T5 : padStr 이 빈 문자열
        T6 : padStr 이 단일 문자열인 경우
        T7 : size 가 str의 길이와 같은 경우
        T8 : size 가 0인 경우
        T9 : size 가 str의 길이보다 작은 경우

        코드 커버리지
        T10 : padStr의 길이가 str에 남아 있는 공백의 길이와 같은 경우
        T11 : padStr의 길이가 str에 남아 있는 공백의 길이보다 큰 경우
        T12 : padStr의 길이가 str에 남아 있는 공백의 길이보다 작은 경우
     */

    @ParameterizedTest
    @MethodSource("generator")
    void test(String originalStr, int size, String padString, String expectedStr){
        assertEquals(expectedStr,LeftPad.leftPad(originalStr, size, padString));
    }

    static Stream<Arguments> generator(){
        return Stream.of(
                of(null, 10, "-", null), // T1
                of("",5,"-","-----"), // T2
                of("hello",-1,"hi","hello"), // T3
                of("abc",5,null,"  abc"), // T4
                of("abc",5,"","  abc"), // T5
                of("abc",5,"-","--abc"), // T6
                of("abc",3,"hello","abc"), // T7
                of("abc",0,"hi","abc"), // T8
                of("abc",2,"hi","abc"), // T9
                of("abc",5,"ab","ababc"), // T10
                of("abc",5,"efg","efabc"), // T11
                of("abc",8,"ef","efefeabc") // T12
        );
    }
}