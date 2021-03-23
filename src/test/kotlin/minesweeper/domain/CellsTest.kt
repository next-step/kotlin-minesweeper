package minesweeper.domain

import minesweeper.domain.Cells.Operation.Result
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
            Cells(listOf(Cell(), Cell()), 2)
        }
    }

    @Test
    internal fun `지뢰가 아닌 셀이 존재해야 한다`() {
        assertThrows<IllegalArgumentException> {
            Cells(listOf(Cell(bomb = true), Cell(bomb = true)), 2)
        }
    }
    @Test
    internal fun `열릴 위치를 입력받으면 셀이 열려있다`() {
        val target = Cell()
        val operation = Cells(listOf(target, Cell(true), Cell()), 3).operation()
        operation.open(Position(1, 1))
        assertThat(operation.result).isEqualTo(Result.SUCCESS)
        assertThat(target.open).isTrue()
    }

    @Test
    internal fun `열려 있는 곳을 다시 열면 이미 열려있음 응답을 받는다`() {
        val cell = Cell()
        cell.open()

        val operation = Cells(listOf(cell, Cell(true)), 2).operation()
        operation.open(Position(1, 1))
        assertThat(operation.result).isEqualTo(Result.OPENED)
    }

    @Test
    internal fun `지뢰를 열면 폭파됨 응답을 받는다`() {
        val operation = Cells(listOf(Cell(), Cell(true)), 2).operation()
        operation.open(Position(2, 1))
        assertThat(operation.result).isEqualTo(Result.EXPLOSION)
    }

    @Test
    internal fun `열었을 때 옆 셀이 비어있으면 같이 열린다`() {
        val first = Cell()
        val second = Cell(count = 1)
        val operation = Cells(listOf(first, second, Cell(true)), 3).operation()
        operation.open(Position(1, 1))
        assertThat(first.open).isTrue()
        assertThat(second.open).isTrue()
    }

    @Test
    fun `열었을 때 옆 셀이 여러개 있으면 같이 열린다`() {
        val first = Cell()
        val second = Cell()
        val third = Cell(count = 1)
        val bomb = Cell(true)
        val operation = Cells(listOf(first, second, third, bomb), 4).operation()
        operation.open(Position(1, 1))
        assertThat(first.open).isTrue()
        assertThat(second.open).isTrue()
        assertThat(third.open).isTrue()
        assertThat(bomb.open).isFalse()
    }

    @Test
    internal fun `두줄도 열린다`() {
        val cells = listOf(
            Cell(), Cell(), Cell(count = 1), Cell(true),
            Cell(), Cell(), Cell(count = 1), Cell(count = 1),
            Cell(), Cell(), Cell(), Cell()
        )
        val operation = Cells(cells, 4).operation()
        operation.open(Position(1, 1))
        assertThat(cells.filter { !it.bomb })
            .hasSize(11)
            .allSatisfy {
                assertThat(it.open).isTrue()
            }
        assertThat(cells.filter { it.bomb })
            .hasSize(1)
            .allSatisfy {
                assertThat(it.open).isFalse()
            }
    }

    @Test
    internal fun `지뢰카운트가 1 이상이면 더 열리지 않는다`() {
        val notOpenedCell = Cell(count = 1)
        val openedCell = Cell(count = 1)
        val cells = listOf(
            Cell(), openedCell, notOpenedCell,
            Cell(), Cell(count = 1), Cell(true)
        )
        val operation = Cells(cells, 3).operation()
        operation.open(Position(1, 1))
        assertThat(openedCell.open).isTrue()
        assertThat(notOpenedCell.open).isFalse()
    }

    @Test
    fun `모두 열리면 종료응답을 받는다`() {
        val first = Cell()
        val operation = Cells(listOf(first, Cell(true)), 2).operation()
        operation.open(Position(1, 1))
        assertThat(operation.result).isEqualTo(Result.END)
    }

    @Test
    fun `영역을 벗어난곳을 열면 OUT_OF_MATRIX 응답을 받는다`() {
        val first = Cell()
        val operation = Cells(listOf(first, Cell(true)), 2).operation()
        operation.open(Position(10, 10))
        assertThat(operation.result).isEqualTo(Result.OUT_OF_MATRIX)
    }
}
