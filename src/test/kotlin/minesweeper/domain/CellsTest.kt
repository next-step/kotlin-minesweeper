package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CellsTest {
    @Test
    internal fun `높이와 너비가 0보다 크다`() {
        assertThrows<IllegalArgumentException> {
            Cells(listOf(Cell(), Cell(bomb = true)), 3)
        }
    }

    @Test
    internal fun `지뢰가 존재해야 한다`() {
        assertThrows<IllegalArgumentException> {
            Cells(listOf(Cell(), Cell()), 3)
        }
    }

    @Test
    internal fun `열릴 위치를 입력받으면 셀이 열려있다`() {
        val cell = Cell()
        Cells(listOf(cell, Cell(true)), 2).open(Position(1, 1))
        assertThat(cell.open).isTrue()
    }

    @Test
    internal fun `열려 있는 곳을 다시 열면 이미 열려있음 응답을 받는다`() {
        val cell = Cell()
        val operation = Cells(listOf(cell, Cell(true)), 2).operation()
        operation.open(Position(1, 1))
        assertThat(operation.result()).isEqualTo(Cells.Operation.Result.OPENED)
    }
}
