package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CellTest {
    @Test
    fun `지뢰가 없는 셀을 생성할 수 있다`() {
        val cell = Cell(position = Position(1, 1))

        val isMine = cell.isMine

        assertThat(isMine).isFalse()
    }

    @Test
    fun `지뢰가 있는 셀을 생성할 수 있다`() {
        val cell = Cell.createMine()

        val isMine = cell.isMine

        assertThat(isMine).isTrue()
    }

    @Test
    fun `셀은 위치를 가진다`() {
        val cell = Cell(position = Position(1, 1))

        val position = cell.position

        assertThat(position).isEqualTo(Position(1, 1))
    }
}