package minesweeper

import minesweeper.domain.BoardSize
import minesweeper.domain.LengthNumber
import minesweeper.domain.MineNumber
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MineNumberTest {
    @DisplayName("지뢰숫자 유효성 확인, 오류사항")
    @ParameterizedTest
    @ValueSource(strings = ["100", "-1"])
    fun validateMineNumber(number: String) {
        val boardSize = BoardSize(LengthNumber(10), LengthNumber(7))
        assertThatThrownBy { MineNumber(number, boardSize) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @DisplayName("지뢰숫자 유효성 확인")
    @Test
    fun validateMineNumber() {
        val boardSize = BoardSize(LengthNumber(10), LengthNumber(7))
        assertThat(MineNumber("15", boardSize))
            .isNotInstanceOf(Exception::class.java)
    }
}
