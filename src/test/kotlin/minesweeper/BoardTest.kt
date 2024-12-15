package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BoardTest {
    @Test
    fun `지뢰 갯수 지정 가능하다`() {
        val fixture = testFixture(
            mutableMapOf(
                Position(0, 0) to CellType.MINE,
                Position(0, 1) to CellType.EMPTY,
                Position(0, 2) to CellType.MINE,
            )
        )
        val board = Board.initializeBoard(Dimensions(3, 3), 1, fixture)

        assertAll(
            { assertThat(board.checkMine(Position(0, 0))).isTrue() },
            { assertThat(board.checkMine(Position(0, 1))).isFalse() },
            { assertThat(board.checkMine(Position(0, 2))).isTrue() },
        )
    }

    private fun testFixture(data: MutableMap<Position, CellType>) = object : PositionProvider {
        override fun provide(dimensions: Dimensions, mineCount: Int): MutableMap<Position, CellType> {
            return data
        }
    }
}