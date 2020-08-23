package minesweeper.domain.cell

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CellTest {

    private val defaultPosition = Pair(1, 1).toPosition()
    private val otherPosition = Pair(2, 2).toPosition()

    @DisplayName(value = "Position이 같은 Cell은 동일한 Cell이다.")
    @Test
    fun checkSamePosition() {
        val cell1 = Cell(defaultPosition)
        val cell2 = MineCell(defaultPosition)
        assertThat(cell1).isEqualTo(cell2)
        assertThat(cell1.hashCode()).isSameAs(cell2.hashCode())
    }

    @DisplayName(value = "Position에 따라 isPosition이 정상값으로 나와야한다.")
    @Test
    fun checkIsPositionNot() {
        val cell = Cell(defaultPosition)
        assertThat(cell.isPosition(defaultPosition)).isTrue()
        assertThat(cell.isPosition(otherPosition)).isFalse()
    }

    @DisplayName(value = "지뢰 Cell open시, 지뢰칸이 여야 한다.")
    @Test
    fun checkOpenMineCell() {
        val cell = MineCell(defaultPosition)
        assertThat(cell.open(3).isMine()).isTrue()
        assertThat(cell.open(3)).isInstanceOf(MineCell::class.java)
    }

    @DisplayName(value = "일반 Cell open시, NumberCell칸이 여야 한다.")
    @Test
    fun checkOpenNumberCell() {
        val cell = Cell(defaultPosition)
        assertThat(cell.open(3).isMine()).isFalse()
        assertThat(cell.open(3)).isInstanceOf(NumberCell::class.java)
    }
}
