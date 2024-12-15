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
}