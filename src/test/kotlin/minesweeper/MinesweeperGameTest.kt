package minesweeper

import minesweeper.domain.BoardSize
import minesweeper.domain.MinesweeperGame
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MinesweeperGameTest {
    @ParameterizedTest
    @ValueSource(strings = ["r", "-1", "#", ""])
    @DisplayName("게임 시작시, 입력값 확인, 오류사 ")
    fun validateMinesweeperGame(number: String) {
        assertThatThrownBy {
            MinesweeperGame(number, "10", "10")
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    @DisplayName("게임 시작시, 입력값 확인 ")
    fun validateMinesweeperGame() {
        val height = "10"
        val width = "7"
        val mineCount = "20"

        val minesweeperGame = MinesweeperGame(height, width, mineCount)
        val minesweeperBoard = minesweeperGame.minesweeperBoard
        assertAll(
            {
                assertThat(minesweeperBoard.boardSize).isEqualTo(BoardSize(height, width))
            },
            {
                assertThat(minesweeperBoard.mineNumber.mineNumber).isEqualTo(mineCount.toInt())
            }
        )
    }
}
