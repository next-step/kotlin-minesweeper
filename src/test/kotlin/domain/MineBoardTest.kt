package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class MineBoardTest {
    @Test
    fun `지뢰판의 초기값은 벽돌객체다`() {
        assertDoesNotThrow {
            MineBoard(3, 3).boards.flatten().all { it is Brick }
        }
    }

    @Test
    fun `지뢰를 심으면 지뢰판의 해당 값은 지뢰 객체다`() {
        val mineBoard = MineBoard(3, 3)
        val (x, y) = Pair(2, 2)
        mineBoard.setMines(listOf(Mine(x, y)))

        assertThat(mineBoard.boards[x][y] is Mine).isEqualTo(true)
    }

    @Test
    fun `지뢰를 심는 개수만큼 지뢰가 존재한다`() {
        val (width, height) = Pair(3, 3)
        val mineBoard = MineBoard(width, height)
        val mines = (0 until 3).map { MineFactory.of(width, height) }
        mineBoard.setMines(mines)
        val minesInBoard = mineBoard.boards.flatten().filterIsInstance<Mine>()

        assertThat(minesInBoard.size).isEqualTo(3)
    }
}
