package minesweeper.state

import minesweeper.domain.Board
import minesweeper.domain.Height
import minesweeper.domain.MineCount
import minesweeper.domain.Position
import minesweeper.domain.Width
import minesweeper.domain.state.Finished.Companion.OPEN_UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE
import minesweeper.domain.state.Win
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WinTest {

    private lateinit var win: Win

    @BeforeEach
    internal fun setUp() {
        val width = Width.from(7)
        val height = Height.from(7)
        val mineCount = MineCount.from(7)

        val board = Board.ofSizeAndMineCount(width, height, mineCount)
        win = Win(board)
    }

    @Test
    fun `Win은 Finished 상태이다`() {
        val isWinFinished = win.isFinished()

        Assertions.assertThat(isWinFinished).isTrue
    }

    @Test
    fun `Win은 open()을 사용할 수 없다`() {
        Assertions.assertThatExceptionOfType(UnsupportedOperationException::class.java)
            .isThrownBy {
                win.open(Position.from("1, 1"))
            }
            .withMessage(OPEN_UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE)
    }
}
