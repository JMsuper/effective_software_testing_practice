package org.example.ch5;

import net.jqwik.api.*;
import net.jqwik.api.constraints.FloatRange;

import static org.junit.jupiter.api.Assertions.*;

class PassingGradeTest {

    private final PassingGrade pg = new PassingGrade();

    @Property
    void fail(
            @ForAll
            @FloatRange(min = 1f, max = 5f, maxIncluded = false)
            float grade){
        assertFalse(pg.passed(grade));
    }

    @Property
    void pass(
            @ForAll
            @FloatRange(min = 5f, max = 10f, maxIncluded = true)
            float grade){
        assertTrue(pg.passed(grade));
    }

    @Property
    void invalid(
            @ForAll("invalidGrades")
            float grade){
        assertThrows(IllegalArgumentException.class,()->pg.passed(grade));
    }

    // Aribitrary : jqwik가 생성될 임의의 값을 다루는 방법
    @Provide
    private Arbitrary<Float> invalidGrades(){
        return Arbitraries.oneOf(
                Arbitraries.floats().lessThan(1f),
                Arbitraries.floats().greaterThan(10f)
        );
    }

}