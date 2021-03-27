package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BoardTest {

    @Test
    fun `board 생성`() {
        val staticMinePositions = fun(boardSpec: BoardSpec): List<Position> {
            return listOf(Position(NaturalNumber(0), NaturalNumber(0)))
        }

        val board = Board.createBoard(BoardSpec(NaturalNumber(2), NaturalNumber(2), NaturalNumber(1)), staticMinePositions)
        assertThat(board.cellRows[0].cells[0]).isEqualTo(Cell(true))
    }
}
