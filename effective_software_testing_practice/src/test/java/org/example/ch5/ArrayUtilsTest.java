package org.example.ch5;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ArrayUtilsTest {
    // - 정수 array
    //   - null
    //   - 단일 요소
    //   - 여러 요소
    // - valueToFind
    //   - 임의의 정수
    // - startIndex
    //   - 음수인 경우
    //   - 0[경계]
    //   - 양수인 경우
    // - (array, startIndex)
    //   - array의 범위 안에 startIndex가 있는 경우
    //   - array의 범위 안에 startIndex가 없는 경우
    // - (array, valueToFind)
    //   - array에 valueToFind가 없는 경우
    //   - array에 valueToFind가 있는 경우
    //   - array에 valueToFind가 여러개 있는 경우
    // - (array, valueToFind, startIndex)
    //   - array에 valueToFind가 있지만, startIndex 이전에 위치한 경우
    //   - array에 valueToFind가 있지만, startIndex 이후에 위치한 경우
    //   - array에 valueToFind가 있지만, 정확하게 startIndex에 위치한 경우[경계]
    //   - array에 valueToFind가 여러 번 있고, startIndex 이후에 위치한 경우
    //   - array에 valueToFind가 여러 번 있고, 하나는 startIndex 이전에, 하나는 이후에 위치한 경우

    @Property
    void indexOfShouldFindFirstValue(
                    @ForAll
                    @Size(value = 100) List<@IntRange(min = -1000, max = 1000) Integer> numbers,
                    @ForAll
                    @IntRange(min = 1001, max = 2000) int value,
                    @ForAll
                    @IntRange(max = 99) int indexToAddElement,
                    @ForAll
                    @IntRange(max = 99) int startIndex
            ){
        numbers.add(indexToAddElement, value);
        int[] array = convertListToArray(numbers);

        int expectedIndex = indexToAddElement >= startIndex ?
                indexToAddElement : -1;
        assertEquals(expectedIndex, ArrayUtils.indexOf(array, value, startIndex));
    }

    private int[] convertListToArray(List<Integer> numbers){
        int[] array = numbers.stream().mapToInt(x -> x).toArray();
        return array;
    }
}