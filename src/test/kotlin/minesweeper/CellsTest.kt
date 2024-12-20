package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CellsTest {
    @Test
    fun `셀의 이웃 지뢰 갯수를 알 수 있다`() {
        val cells =
            Cells.detectCreateOf(
                listOf(
                    NumberCell(Position(0, 0)),
                    MineCell(Position(1, 0)),
                    MineCell(Position(1, 1)),
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
                    NumberCell(Position(0, 0)),
                    NumberCell(Position(1, 0)),
                    NumberCell(Position(2, 0)),
                    NumberCell(Position(0, 1)),
                    NumberCell(Position(1, 1)),
                    NumberCell(Position(2, 1)),
                    NumberCell(Position(0, 2)),
                    NumberCell(Position(1, 2)),
                    NumberCell(Position(2, 2)),
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
                    MineCell(Position(0, 0)),
                    NumberCell(Position(1, 0)),
                    NumberCell(Position(0, 1)),
                    NumberCell(Position(1, 1)),
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
                    NumberCell(Position(0, 0)),
                    NumberCell(Position(1, 0)),
                    NumberCell(Position(2, 0)),
                    NumberCell(Position(0, 1)),
                    NumberCell(Position(1, 1)),
                    NumberCell(Position(2, 1)),
                    NumberCell(Position(0, 2)),
                    NumberCell(Position(1, 2)),
                    MineCell(Position(2, 2)),
                ),
            )

        cells.open(Position(0, 0))

        cells.values.values.forEach {
            println("position: ${it.position}, neighborMineCount: ${it.neighborMineCount} isOpen: ${it.isOpen}")
        }

        assertAll(
            { assertThat(cells.at(Position(0, 0)).isOpen).isTrue() },
            { assertThat(cells.at(Position(1, 0)).isOpen).isTrue() },
            { assertThat(cells.at(Position(2, 0)).isOpen).isTrue() },
            { assertThat(cells.at(Position(0, 1)).isOpen).isTrue() },
            { assertThat(cells.at(Position(0, 2)).isOpen).isTrue() },
            { assertThat(cells.at(Position(1, 1)).isOpen).isFalse() },
            { assertThat(cells.at(Position(2, 1)).isOpen).isFalse() },
            { assertThat(cells.at(Position(1, 2)).isOpen).isFalse() },
            { assertThat(cells.at(Position(2, 2)).isOpen).isFalse() },
        )
    }
}
