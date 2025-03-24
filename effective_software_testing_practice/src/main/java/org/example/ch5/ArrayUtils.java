package org.example.ch5;


public class ArrayUtils {
    // 요구사항
    // 1. 입력이 null 이면 -1 반환
    // 2. startIndex가 음수이면 0 취급
    // 3. 배열의 길이보다 큰 startIndex는 -1 반환
    public static int indexOf(final int[] array, final int valueToFind, int startIndex){
        if (array == null){
            return -1;
        }

        if (startIndex < 0){
            startIndex = 0;
        }

        for(int i = startIndex; i < array.length; i++){
            if(valueToFind == array[i]){
                return i;
            }
        }

        return -1;
    }
}
