package org.example.ch3;

public class LeftPad {

    public static final String SPACE = " ";

    /**
     * 주어진 문자열의 좌측에 다른 문자를 덧붙인다. size의 크기가 되도록 덧붙인다.
     * @param str 주어진 문자열. null이 될 수 있음
     * @param size 합친 문자열의 크기
     * @param padStr 덧붙일 문자열. 널 또는 빈 문자열은 공백 한 칸으로 취급
     * @return
     */
    public static String leftPad(final String str, final int size, String padStr){

        if (str == null){
            return null;
        }

        if (padStr == null || padStr.isEmpty()){
            padStr = SPACE;
        }

        final int padLen = padStr.length();
        final int strLen = str.length();
        final int pads = size - strLen;

        if (pads <= 0){
            return str;
        }

        if (pads == padLen){
            return padStr.concat(str);
        }else if (pads < padLen){
            return padStr.substring(0,pads).concat(str);
        }else{
            final char[] padding = new char[pads];
            final char[] padChars = padStr.toCharArray();

            for(int i = 0; i < pads; i++){
                padding[i] = padChars[i % padLen];
            }

            return new String(padding).concat(str);
        }


    }
}
