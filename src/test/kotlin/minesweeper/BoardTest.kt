package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BoardTest {
    @Test
    fun `지뢰 갯수 지정 가능하다`() {
        val fixture =
            testFixture(
                listOf(
                    Cell.MineCell(Position(0, 0)),
                    Cell.NumberCell(Position(0, 1)),
                    Cell.MineCell(Position(0, 2)),
                ),
            )
        val board = Board.initializeBoard(Dimensions(3, 3), fixture)

        assertAll(
            { assertThat(board.checkMine(Position(0, 0))).isTrue() },
            { assertThat(board.checkMine(Position(0, 1))).isFalse() },
            { assertThat(board.checkMine(Position(0, 2))).isTrue() },
        )
    }

    private fun testFixture(data: List<Cell>) =
        object : CellProvider {
            override fun provide(dimensions: Dimensions): Cells {
                return Cells.create(data)
            }
        }
}
