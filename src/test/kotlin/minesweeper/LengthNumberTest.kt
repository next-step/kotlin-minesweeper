package minesweeper

import minesweeper.domain.LengthNumber
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LengthNumberTest {
    @DisplayName("높이, 너비 유효성 확인, 오류사항")
    @ParameterizedTest
    @ValueSource(strings = ["40000", "-1", "", "$", ""])
    fun validateLengthNumber(number: String) {
        assertThatThrownBy { LengthNumber(number) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @DisplayName("높이, 너비 유효성 확인")
    @Test
    fun validateLengthNumber() {
        val lengthNumber = LengthNumber("10")
        assertThat(lengthNumber.length)
            .isEqualTo(10)
    }
}
