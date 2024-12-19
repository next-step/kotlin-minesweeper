package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CellsTest {
    @Test
    fun `위치와 지뢰 타입을 가진다`() {
        val cells =
            Cells.create(
                listOf(
                    Cell.NumberCell(Position(0, 0)),
                    Cell.MineCell(Position(1, 1)),
                ),
            )

        assertAll(
            { assertThat(cells.checkMine(Position(0, 0))).isFalse() },
            { assertThat(cells.checkMine(Position(1, 1))).isTrue() },
        )
    }

    @Test
    fun `행 갯수를 알 수 있다`() {
        val cells =
            Cells.create(
                listOf(
                    Cell.NumberCell(Position(0, 0)),
                    Cell.NumberCell(Position(1, 0)),
                ),
            )

        assertThat(cells.rowSize()).isEqualTo(1)
    }

    @Test
    fun `해당 행의 셀 타입을 알 수 있다`() {
        val cells =
            Cells.create(
                listOf(
                    Cell.NumberCell(Position(0, 0)),
                    Cell.MineCell(Position(1, 0)),
                ),
            )

        assertThat(cells.rowAt(0).map { it }).containsExactly(
            Cell.NumberCell(Position(0, 0)),
            Cell.MineCell(Position(1, 0)),
        )
    }

    @Test
    fun `셀의 이웃 지뢰 갯수를 알 수 있다`() {
        val cells =
            Cells.create(
                listOf(
                    Cell.NumberCell(Position(0, 0)),
                    Cell.MineCell(Position(1, 0)),
                    Cell.MineCell(Position(1, 1)),
                ),
            )

        val actual = cells.neighborsMineCount(Position(0, 0))

        assertThat(actual).isEqualTo(2)
    }

    @Test
    fun `셀은 지뢰를 지정 할 수 있다`() {
        val cells =
            Cells.create(
                listOf(
                    Cell.NumberCell(Position(0, 0)),
                    Cell.NumberCell(Position(1, 0)),
                    Cell.NumberCell(Position(0, 1)),
                    Cell.NumberCell(Position(1, 1)),
                    Cell.NumberCell(Position(0, 2)),
                    Cell.NumberCell(Position(1, 2)),
                ),
            )

        val actual = cells.assignMinesToCells(listOf(Position(0, 0), Position(1, 1)))

        assertAll(
            { assertThat(actual.checkMine(Position(0, 0))).isTrue() },
            { assertThat(actual.checkMine(Position(1, 1))).isTrue() },
            { assertThat(actual.checkMine(Position(0, 1))).isFalse() },
            { assertThat(actual.checkMine(Position(1, 0))).isFalse() },
        )
    }
}
