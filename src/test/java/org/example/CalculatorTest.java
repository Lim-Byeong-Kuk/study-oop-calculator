package org.example;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Streams;
import org.example.calculate.PositiveNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * 요구사항
 * • 간단한 사칙연산을 할 수 있다.
 * • 양수로만 계산할 수 있다.
 * • 나눗셈에서 0을 나누는 경우 IllegalArgument 예외를 발생시킨다.
 * • MVC패턴(Model-View-Controller) 기반으로 구현한다.
 */
public class CalculatorTest {

    @DisplayName("덧셈 연산을 수행한다")
    @Test
    void additionTest() {

        int result = Calculator.calculate(new PositiveNumber(1), "+", new PositiveNumber(2));

        assertThat(result).isEqualTo(3);
    }

    @DisplayName("뺄셈 연산을 수행한다")
    @Test
    void subtractionTest() {

        int result = Calculator.calculate(new PositiveNumber(1), "-", new PositiveNumber(2));

        assertThat(result).isEqualTo(-1);
    }

    /**
     * 하나의 테스트 코드로 네 가지 사칙연산 테스트 수행하기
     */
    @DisplayName("사칙연산을 수행한다.")
    @ParameterizedTest
    @MethodSource("formulaAndResult")  // "formulaAndResult 메소드를 인자로 받겠다는 뜻
    void calculateTest(int operand1, String operator, int operand2, int result) {
        int calculateResult = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));

        assertThat(calculateResult).isEqualTo(result);
    }

    private static Stream<Arguments> formulaAndResult() {
        return Stream.of(
                arguments(1, "+", 2, 3),
                arguments(1, "-", 2, -1),
                arguments(4, "*", 2, 8),
                arguments(4, "/", 2, 2)
        );
    }
}
