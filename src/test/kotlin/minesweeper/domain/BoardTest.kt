package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BoardTest {

    @Test
    fun `board 생성`() {
        val boardSpec = BoardSpec(
            width = NaturalNumber(2),
            height = NaturalNumber(2),
            mineCount = NaturalNumber(1)
        )

        val board = Board.createBoard(boardSpec, listOf(Position(0, 0)))
        assertThat(board.cells.get(Position(0, 0))).isInstanceOf(MineCell::class.java)
    }
}
