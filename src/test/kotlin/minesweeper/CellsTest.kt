package minesweeper

import minesweeper.domain.Cell
import minesweeper.domain.Cells
import minesweeper.domain.Column
import minesweeper.domain.Height
import minesweeper.domain.Position
import minesweeper.domain.Positions
import minesweeper.domain.Row
import minesweeper.domain.Width
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CellsTest {

    private lateinit var positionList: List<Position>
    private lateinit var minePositionList: List<Position>

    @BeforeEach
    internal fun setUp() {
        positionList = Positions.of(Width.from(3), Height.Companion.from(3)).positions

        minePositionList = listOf(
            Position(Row.from(0), Column.from(0)),
            Position(Row.from(1), Column.from(1))
        )
    }

    @Test
    fun `Cell List를 통해 Cells를 생성할 수 있다`() {
        val cellList = listOf(
            Position(Row.from(0), Column.from(0)),
            Position(Row.from(1), Column.from(1))
        ).map { Cell.MineCell(it) }

        val cells = Cells.from(cellList)

        assertThat(cells.cells).isEqualTo(cellList)
    }

    @Test
    fun `Position List와 Mine들의 Position List를 통 Cells를 생성할 수 있다`() {
        val cells = Cells.of(positionList, minePositionList)

        assertThat(cells.cells.size).isEqualTo(9)
    }

    @Test
    fun `Position List와 Mine들의 Position List를 통해 만든 Cells는 Mine을 가지고 있다`() {
        val cells = Cells.of(positionList, minePositionList)

        val mine1 = cells.cells.find { it.position == Position(Row.from(0), Column.from(0)) }
        val mine2 = cells.cells.find { it.position == Position(Row.from(1), Column.from(1)) }

        assertAll(
            { assertThat(mine1).isInstanceOf(Cell.MineCell::class.java) },
            { assertThat(mine2).isInstanceOf(Cell.MineCell::class.java) }
        )
    }
}
