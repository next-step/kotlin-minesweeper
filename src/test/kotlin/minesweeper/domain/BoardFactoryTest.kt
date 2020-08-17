package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardFactoryTest {
    @Test
    fun make_board_10x10() {
        val board = BoardFactory.makeBoard(10, 10) { listOf() }

        assertThat(board.points).hasSize(100)
    }

    @Test
    fun set_mine() {
        val board = BoardFactory.makeBoard(10, 10) { BoardFactory.makeMineCoordinates(it, 10) }

        val mineCount = board.countMine()

        assertThat(mineCount).isEqualTo(10)
    }
}
