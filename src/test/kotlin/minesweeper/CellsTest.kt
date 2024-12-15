package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CellsTest {
    @Test
    fun `위치와 지뢰 타입을 가진다`() {
        val cells = Cells(
            mutableMapOf(
                Position(0, 0) to CellType.EMPTY,
                Position(1, 1) to CellType.MINE,
            )
        )

        assertAll(
            { assertThat(cells.checkMine(Position(0, 0))).isFalse() },
            { assertThat(cells.checkMine(Position(1, 1))).isTrue() }
        )
    }

    @Test
    fun `행 갯수를 알 수 있다`() {
        val cells = Cells(
            mutableMapOf(
                Position(0, 0) to CellType.EMPTY,
                Position(1, 0) to CellType.EMPTY,
            )
        )

        assertThat(cells.rowSize()).isEqualTo(1)
    }

    @Test
    fun `해당 행의 셀 타입을 알 수 있다`() {
        val cells = Cells(
            mutableMapOf(
                Position(0, 0) to CellType.EMPTY,
                Position(1, 0) to CellType.MINE,
            )
        )

        assertThat(cells.rowAt(0)).containsExactly(CellType.EMPTY, CellType.MINE)
    }
}