package minesweeper.state

import minesweeper.domain.Board
import minesweeper.domain.Height
import minesweeper.domain.MineCount
import minesweeper.domain.Position
import minesweeper.domain.Width
import minesweeper.domain.state.Finished.Companion.OPEN_UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE
import minesweeper.domain.state.Lose
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class LoseTest {

    private lateinit var lose: Lose

    @BeforeEach
    internal fun setUp() {
        val width = Width.from(7)
        val height = Height.from(7)
        val mineCount = MineCount.from(7)

        val board = Board.ofSizeAndMineCount(width, height, mineCount)
        lose = Lose(board)
    }

    @Test
    fun `Lose는 Finished 상태이다`() {
        val isLoseFinished = lose.isFinished()

        assertThat(isLoseFinished).isTrue
    }

    @Test
    fun `Lose는 open()을 사용할 수 없다`() {
        Assertions.assertThatExceptionOfType(UnsupportedOperationException::class.java)
            .isThrownBy {
                lose.open(Position.from("1, 1"))
            }
            .withMessage(OPEN_UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE)
    }
}
