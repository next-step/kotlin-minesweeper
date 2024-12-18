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
                    Cell(CellType.DEFAULT, Position(0, 0)),
                    Cell(CellType.Mine, Position(1, 1)),
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
                    Cell(CellType.DEFAULT, Position(0, 0)),
                    Cell(CellType.DEFAULT, Position(1, 0)),
                ),
            )

        assertThat(cells.rowSize()).isEqualTo(1)
    }

    @Test
    fun `해당 행의 셀 타입을 알 수 있다`() {
        val cells =
            Cells.create(
                listOf(
                    Cell(CellType.DEFAULT, Position(0, 0)),
                    Cell(CellType.Mine, Position(1, 0)),
                ),
            )

        assertThat(cells.rowAt(0).map { it.type }).containsExactly(CellType.DEFAULT, CellType.Mine)
    }

    @Test
    fun `셀의 이웃 지뢰 갯수를 알 수 있다`() {
        val cells =
            Cells.create(
                listOf(
                    Cell(CellType.DEFAULT, Position(0, 0)),
                    Cell(CellType.Mine, Position(1, 0)),
                    Cell(CellType.Mine, Position(1, 1)),
                ),
            )

        val actual = cells.neighborsMineCount(Position(0, 0))

        assertThat(actual).isEqualTo(2)
    }
}
