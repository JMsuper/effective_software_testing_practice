package org.example.ch5;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UniqueTest {

    @Property
    void unique(
            @ForAll
            @Size(value = 100)
            List<@IntRange(min = 1, max = 20) Integer>
            numbers){
        int[] doubles = convertListToArray(numbers);
        int[] result = Unique.unique(doubles);

        // - 모든 요소를 포함하고 있는가?
        // - 중복된 내용은 없는가?
        // - 내림차순으로 정렬된 상태인가?
        assertThat(result)
                .contains(doubles)
                .doesNotHaveDuplicates()
                .isSortedAccordingTo(Comparator.reverseOrder());
    }

    private int[] convertListToArray(List<Integer> numbers){
        int[] array = numbers
                .stream()
                .mapToInt(x -> x)
                .toArray();

        return array;
    }
}