package minesweeper.domain.cell

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CellTest {
    @DisplayName(value = "Position이 같은 Cell은 동일한 Cell이다.")
    @Test
    fun checkSamePosition() {
        val cell1 = Cell(Position(1, 1))
        val cell2 = MineCell(Position(1, 1))
        assertThat(cell1).isEqualTo(cell2)
        assertThat(cell1.hashCode()).isSameAs(cell2.hashCode())
    }
}
