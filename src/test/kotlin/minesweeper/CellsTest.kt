package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CellsTest {
    @Test
    fun `위치와 지뢰 타입을 가진다`() {
        val cells =
            Cells.detectCreateOf(
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
            Cells.detectCreateOf(
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
            Cells.detectCreateOf(
                listOf(
                    Cell.NumberCell(Position(0, 0)),
                    Cell.MineCell(Position(1, 0)),
                ),
            )

        val actual = cells.rowAt(0)

        assertThat(actual.size).isEqualTo(2)
    }

    @Test
    fun `셀의 이웃 지뢰 갯수를 알 수 있다`() {
        val cells =
            Cells.detectCreateOf(
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
    fun `주위셀을 열 수 있다`() {
        val cells =
            Cells.detectCreateOf(
                listOf(
                    Cell.NumberCell(Position(0, 0)),
                    Cell.NumberCell(Position(1, 0)),
                    Cell.NumberCell(Position(2, 0)),
                    Cell.NumberCell(Position(0, 1)),
                    Cell.NumberCell(Position(1, 1)),
                    Cell.NumberCell(Position(2, 1)),
                    Cell.NumberCell(Position(0, 2)),
                    Cell.NumberCell(Position(1, 2)),
                    Cell.NumberCell(Position(2, 2)),
                ),
            )

        cells.open(Position(0, 0))

        assertAll(
            { assertThat(cells.at(Position(0, 0)).isOpen).isTrue() },
            { assertThat(cells.at(Position(1, 0)).isOpen).isTrue() },
            { assertThat(cells.at(Position(2, 0)).isOpen).isTrue() },
            { assertThat(cells.at(Position(0, 1)).isOpen).isTrue() },
            { assertThat(cells.at(Position(1, 1)).isOpen).isTrue() },
            { assertThat(cells.at(Position(2, 1)).isOpen).isTrue() },
            { assertThat(cells.at(Position(0, 2)).isOpen).isTrue() },
            { assertThat(cells.at(Position(1, 2)).isOpen).isTrue() },
            { assertThat(cells.at(Position(2, 2)).isOpen).isTrue() },
        )
    }

    @Test
    fun `자신만여는경우`() {
        val cells =
            Cells.detectCreateOf(
                listOf(
                    Cell.MineCell(Position(0, 0)),
                    Cell.NumberCell(Position(1, 0)),
                    Cell.NumberCell(Position(0, 1)),
                    Cell.NumberCell(Position(1, 1)),
                ),
            )

        cells.open(Position(1, 0))

        assertAll(
            { assertThat(cells.at(Position(0, 0)).isOpen).isFalse() },
            { assertThat(cells.at(Position(1, 0)).isOpen).isTrue() },
            { assertThat(cells.at(Position(0, 1)).isOpen).isFalse() },
            { assertThat(cells.at(Position(1, 1)).isOpen).isFalse() },
        )
    }

    @Test
    fun `인접한 셀의 지뢰개수가 0이면 모두 연다`() {
        val cells =
            Cells.detectCreateOf(
                listOf(
                    Cell.NumberCell(Position(0, 0)),
                    Cell.NumberCell(Position(1, 0)),
                    Cell.NumberCell(Position(2, 0)),
                    Cell.NumberCell(Position(0, 1)),
                    Cell.NumberCell(Position(1, 1)),
                    Cell.NumberCell(Position(2, 1)),
                    Cell.NumberCell(Position(0, 2)),
                    Cell.NumberCell(Position(1, 2)),
                    Cell.MineCell(Position(2, 2)),
                ),
            )

        cells.open(Position(0, 0))

        assertAll(
            { assertThat(cells.at(Position(0, 0)).isOpen).isTrue() },
            { assertThat(cells.at(Position(1, 0)).isOpen).isTrue() },
            { assertThat(cells.at(Position(2, 0)).isOpen).isTrue() },
            { assertThat(cells.at(Position(0, 1)).isOpen).isTrue() },
            { assertThat(cells.at(Position(1, 1)).isOpen).isFalse() },
            { assertThat(cells.at(Position(2, 1)).isOpen).isFalse() },
            { assertThat(cells.at(Position(0, 2)).isOpen).isTrue() },
            { assertThat(cells.at(Position(1, 2)).isOpen).isFalse() },
            { assertThat(cells.at(Position(2, 2)).isOpen).isFalse() },
        )
    }
}
