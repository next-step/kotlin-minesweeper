package com.nextstep.jngcii.minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class StringUtilityTest {
    @ParameterizedTest
    @ValueSource(strings = ["a", "ab", "-1", "0", "!"])
    fun `양의 정수가 아니면 예외 발생`(value: String) {
        assertThrows<IllegalArgumentException>("양의 정수만 입력 가능합니다.") {
            StringUtility.convertToPositiveInt(value)
        }
    }

    @ParameterizedTest
    @CsvSource(value = ["1, 1", "2, 2", "123, 123"])
    fun `양의 정수면 정수로 변환 반환`(value: String, expected: Int) {
        val actual = StringUtility.convertToPositiveInt(value)

        assertThat(actual).isEqualTo(expected)
    }
}
