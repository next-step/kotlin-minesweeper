package minesweeper.domain

import minesweeper.domain.exception.ExceptionReason
import minesweeper.domain.exception.MineSweeperException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MineSweeperBoardTest {

    @Test
    fun `render 메소드는 가로 * 세로와 같은 맵을 반환한다`() {
        val width = 3
        val height = 5

        val board = MineSweeperBoard(width, height)
        assertThat(board.state.size).isEqualTo(15)
        assertThat(board.mineCount).isEqualTo(0)
    }

    @Test
    fun `지뢰를 넣을 수 있다`() {
        val width = 2
        val height = 2

        val board = MineSweeperBoard(width, height, mineCount = 1)
        assertThat(board.state.size).isEqualTo(4)
        assertThat(board.mineCount).isEqualTo(1)
    }

    @Test
    fun `가로 * 세로 길이를 넘어서는 수의 지뢰를 설치할 수는 없다`() {
        val width = 2
        val height = 2

        val exception = assertThrows<MineSweeperException> {
            MineSweeperBoard(width, height, mineCount = 5)
        }

        assertThat(exception.reason).isEqualTo(ExceptionReason.MINE_COUNT_OVER_BLOCKS)
    }
}
