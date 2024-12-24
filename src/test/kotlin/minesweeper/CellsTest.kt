package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

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
    fun `모든 셀이 열린것을 알수 있다`() {
        val cells =
            Cells.detectCreateOf(
                listOf(
                    NumberCell(Position(0, 0)),
                    NumberCell(Position(2, 2)),
                ),
            )

        val actual = cells.allNonMineCellsOpened()

        assertThat(actual).isFalse()
    }
}
