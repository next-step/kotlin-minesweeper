package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CellOpenerTest {
    @Test
    fun `모두 열면 완료`() {
        val cellOpener =
            DefaultNeighborOpener(
                Cells.detectCreateOf(
                    listOf(
                        NumberCell(Position(0, 0)),
                        MineCell(Position(1, 0)),
                    ),
                ),
            )

        val openState = cellOpener.open(Position(0, 0))

        assertThat(openState).isEqualTo(OpenState.ALL_DONE)
    }

    @Test
    fun `지뢰를 열면 패배한다`() {
        val cellOpener =
            DefaultNeighborOpener(
                Cells.detectCreateOf(
                    listOf(
                        NumberCell(Position(0, 0)),
                        MineCell(Position(1, 0)),
                        NumberCell(Position(0, 1)),
                        NumberCell(Position(1, 1)),
                    ),
                ),
            )

        val openState = cellOpener.open(Position(1, 0))

        assertThat(openState).isEqualTo(OpenState.LOSE)
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

        val cellOpener = DefaultNeighborOpener(cells)
        val openState = cellOpener.open(Position(1, 0))

        assertAll(
            { assertThat(openState).isEqualTo(OpenState.CONTINUE) },
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
                    NumberCell(Position(0, 1)),
                    NumberCell(Position(1, 1)),
                ),
            )

        val cellOpener = DefaultNeighborOpener(cells)
        val openState = cellOpener.open(Position(0, 0))

        assertAll(
            { assertThat(openState).isEqualTo(OpenState.ALL_DONE) },
            { assertThat(cells.at(Position(0, 0)).isOpen).isTrue() },
            { assertThat(cells.at(Position(1, 0)).isOpen).isTrue() },
            { assertThat(cells.at(Position(0, 1)).isOpen).isTrue() },
            { assertThat(cells.at(Position(1, 1)).isOpen).isTrue() },
        )
    }
}
