package minesweeper.domain.cell

import minesweeper.domain.board.MineCount
import minesweeper.domain.position.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@Suppress("NonAsciiCharacters")
class CellsTest {

    private val cells = mapOf(
        Position.from(row = 1, column = 1) to BlockCell(MineCount(0)),
        Position.from(row = 1, column = 2) to BlockCell(MineCount(1)),
        Position.from(row = 1, column = 3) to MineCell(),
        Position.from(row = 2, column = 1) to BlockCell(MineCount(0)),
        Position.from(row = 2, column = 2) to BlockCell(MineCount(1)),
        Position.from(row = 2, column = 3) to BlockCell(MineCount(1)),
    )

    /**
     *
     * 0 1 *
     * 0 1 1
     */
    @ParameterizedTest
    @CsvSource(
        value = [
            "1,1,false", "1,2,false", "1,3,true",
            "2,1,false", "2,2,false", "2,3,false",
            "3,1,false"
        ]
    )
    fun `주어진 position에 지뢰가 있는지 확인한다`(row: Int, column: Int, expected: Boolean) {
        val position = Position.from(row = row, column = column)

        val result = Cells(cells).isMine(position)

        assertThat(result).isEqualTo(expected)
    }

    /**
     *
     * 0 1 *
     * 0 1 1
     */
    @Test
    fun `근처에 지뢰가 있는 Cell을 열면 해당 Cell만 열린다`() {
        val given = Cells(cells)

        given.open(Position.from(row = 1, column = 2))

        with(given.cells) {
            assertAll(
                { assertThat(get(1, 1).isOpen).isEqualTo(false) },
                { assertThat(get(1, 2).isOpen).isEqualTo(true) },
                { assertThat(get(2, 1).isOpen).isEqualTo(false) },
                { assertThat(get(2, 2).isOpen).isEqualTo(false) },
                { assertThat(get(2, 3).isOpen).isEqualTo(false) },
            )
        }
    }

    /**
     *
     * 0 1 *
     * 0 1 1
     */
    @Test
    fun `근처에 지뢰가 없는 Cell을 열면 근처 Cell도 모두 열린다`() {
        val given = Cells(cells)

        given.open(Position.from(row = 1, column = 1))

        with(given.cells) {
            assertAll(
                { assertThat(get(1, 1).isOpen).isEqualTo(true) },
                { assertThat(get(1, 2).isOpen).isEqualTo(true) },
                { assertThat(get(2, 1).isOpen).isEqualTo(true) },
                { assertThat(get(2, 2).isOpen).isEqualTo(true) },
                { assertThat(get(2, 3).isOpen).isEqualTo(false) },
            )
        }
    }

    /**
     *
     * 0 1 *
     * 0 1 1
     * 0 0 0
     */
    @Test
    fun `근처에 지뢰가 없는 Cell을 열었을 때 근처 Cell의 근처에도 지뢰가 없다면 인접한 모든 Cell이 열린다`() {
        val cells = this.cells + mapOf(
            Position.from(row = 3, column = 1) to BlockCell(MineCount(0)),
            Position.from(row = 3, column = 2) to BlockCell(MineCount(0)),
            Position.from(row = 3, column = 3) to BlockCell(MineCount(0)),
        )
        val given = Cells(cells)

        given.open(Position.from(row = 2, column = 1))

        val allCellWithoutMine = (given.cells - Position.from(1, 3)).values
        assertThat(allCellWithoutMine.all(Cell::isOpen)).isTrue
    }

    @Test
    fun `지뢰가 아닌 모든 Cell이 열렸는지 확인한다`() {
        val cells = mapOf(
            Position.from(row = 1, column = 1) to BlockCell(MineCount(0), isOpen = true),
            Position.from(row = 1, column = 2) to BlockCell(MineCount(0), isOpen = true),
            Position.from(row = 1, column = 3) to BlockCell(MineCount(0), isOpen = true),
            Position.from(row = 2, column = 1) to BlockCell(MineCount(0), isOpen = true),
            Position.from(row = 2, column = 2) to BlockCell(MineCount(0), isOpen = true),
            Position.from(row = 2, column = 3) to MineCell(isOpen = false),
        )
        val given = Cells(cells)

        given.open(Position.from(row = 2, column = 1))

        assertThat(given.isAllOpen()).isTrue
    }

    /**
     *
     * F 1 *
     * 0 1 1
     */
    @Test
    fun `BlockCell을 flag 처리하면 해당 Cell만 Flag로 바뀐다`() {
        val given = Cells(cells)

        given.flag(Position.from(row = 1, column = 1))

        assertThat(given.cells.get(1, 1)).isInstanceOf(FlagCell::class.java)
    }

    /**
     *
     * F 1 *
     * 0 1 1
     */
    @Test
    fun `기존에 BlockCell인 FlagCell을 flag 처리하면 다시 BlockCell로 바뀐다`() {
        val cells = cells.toMutableMap().apply {
            computeIfPresent(Position.from(row = 1, column = 1)) { _, cell -> FlagCell(cell) }
        }
        val given = Cells(cells)

        given.flag(Position.from(row = 1, column = 1))

        assertThat(given.cells.get(1, 1)).isInstanceOf(BlockCell::class.java)
    }

    /**
     *
     * 0 1 F
     * 0 1 1
     */
    @Test
    fun `MineCell을 flag 처리하면 해당 Cell만 Flag로 바뀐다`() {
        val given = Cells(cells)

        given.flag(Position.from(row = 1, column = 3))

        assertThat(given.cells.get(1, 3)).isInstanceOf(FlagCell::class.java)
    }

    /**
     *
     * 0 1 F
     * 0 1 1
     */
    @Test
    fun `기존에 MineCell인 FlagCell을 flag 처리하면 다시 MineCell로 바뀐다`() {
        val cells = cells.toMutableMap().apply {
            computeIfPresent(Position.from(row = 1, column = 3)) { _, cell -> FlagCell(cell) }
        }
        val given = Cells(cells)

        given.flag(Position.from(row = 1, column = 3))

        assertThat(given.cells.get(1, 3)).isInstanceOf(MineCell::class.java)
    }

    private fun Map<Position, Cell>.get(row: Int, column: Int): Cell {
        return getValue(Position.from(row = row, column = column))
    }
}
