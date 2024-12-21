package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CellTest {
    @Test
    fun `주변 지뢰 개수를 가지는 셀을 생성한다`() {
        val cell = Cell.createDefault(Position(1, 1))

        val actual = cell.neighborMineCount

        assertThat(actual).isEqualTo(0)
    }

    @Test
    fun `주변 지뢰 개수를 지정가능하다`() {
        val cell = NumberCell(Position(1, 1))

        cell.determineCell(3)

        assertThat(cell.neighborMineCount).isEqualTo(3)
    }

    @Test
    fun `지뢰가 있는 셀을 생성할 수 있다`() {
        val cell = Cell.createMine(Position(1, 1))

        assertThat(cell is MineCell).isTrue()
    }

    @Test
    fun `셀은 위치를 가진다`() {
        val cell = Cell.createDefault(Position(1, 1))

        val position = cell.position

        assertThat(position).isEqualTo(Position(1, 1))
    }

    @Test
    fun `셀을 열수 있다`() {
        val cell = Cell.createDefault(Position(1, 1))

        cell.open()

        assertThat(cell.isOpen).isTrue()
    }

    @Test
    fun `Cell 은 초기 닫힌 상태다`() {
        val cell = Cell.createDefault(Position(1, 1))

        assertThat(cell.isOpen).isFalse()
    }
}
