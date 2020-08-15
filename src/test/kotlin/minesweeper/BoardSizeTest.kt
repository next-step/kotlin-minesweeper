package minesweeper

import minesweeper.domain.BoardSize
import minesweeper.domain.LengthNumber
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BoardSizeTest {
    @DisplayName("보드 사이즈 유효성 확인, 오류사항")
    @ParameterizedTest
    @ValueSource(strings = ["40000", "-1", "", "$", ""])
    fun validateBoardSize(number: String) {
        assertThatThrownBy {
            BoardSize(LengthNumber(number), LengthNumber(10))
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @DisplayName("보드 사이즈 유효성 확인")
    @Test
    fun validateBoardSize() {
        val boardSize = BoardSize(LengthNumber(10), LengthNumber(10))
        assertThat(boardSize.height.length)
            .isEqualTo(10)
    }
}
