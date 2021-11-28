package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CellTest {

    @Test
    fun `Cell은 Row와 Column을 가진다`() {
        val cell = Cell.Zero(Position(1, 1))
        assertAll(
            { assertThat(cell.row).isEqualTo(Row(1)) },
            { assertThat(cell.column).isEqualTo(Column(1)) }
        )
    }
}
