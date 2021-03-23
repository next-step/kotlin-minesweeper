package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OperationTest {
    @Test
    internal fun `열릴 위치를 입력받으면 셀이 열려있다`() {
        val target = Cell()
        val operation = Cells(listOf(target, Cell(true), Cell()), 3).operation()
        operation.open(Position(1, 1))

        assertThat(operation.result).isEqualTo(Operation.Result.SUCCESS)
        assertThat(target.open).isTrue()
    }

    @Test
    internal fun `열려 있는 곳을 다시 열면 이미 열려있음 응답을 받는다`() {
        val cell = Cell()
        cell.open()
        val operation = Cells(listOf(cell, Cell(true)), 2).operation()
        operation.open(Position(1, 1))

        assertThat(operation.result).isEqualTo(Operation.Result.OPENED)
    }

    @Test
    internal fun `지뢰를 열면 폭파됨 응답을 받는다`() {
        val operation = Cells(listOf(Cell(), Cell(true)), 2).operation()
        operation.open(Position(2, 1))

        assertThat(operation.result).isEqualTo(Operation.Result.EXPLOSION)
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
        val cells = Cells(listOf(Cell(), Cell(), Cell(count = 1), Cell(true)), 4)
        val operation = cells.operation()

        operation.open(Position(1, 1))

        assertThat(cells)
            .filteredOnAssertions { assertThat(it.open).isTrue() }
            .hasSize(3)

        assertThat(cells)
            .filteredOnAssertions { assertThat(it.open).isFalse() }
            .hasSize(1)
    }

    @Test
    internal fun `열었을 때 다른 줄도 닫혀있으면 열린다`() {
        val cells = Cells(
            listOf(
                Cell(), Cell(), Cell(count = 1), Cell(true),
                Cell(), Cell(), Cell(count = 1), Cell(count = 1),
                Cell(), Cell(), Cell(), Cell()
            ),
            4
        )
        val operation = cells.operation()
        operation.open(Position(1, 1))

        assertThat(cells)
            .filteredOnAssertions { assertThat(it.open).isTrue() }
            .hasSize(11)
            .allSatisfy { assertThat(it.bomb).isFalse() }

        assertThat(cells)
            .filteredOnAssertions { assertThat(it.open).isFalse() }
            .hasSize(1)
            .allSatisfy { assertThat(it.bomb).isTrue() }
    }

    @Test
    internal fun `지뢰카운트가 1 이상이면 더 열리지 않는다`() {
        val notOpenedCell = Cell(count = 1)
        val openedCell = Cell(count = 1)
        val operation = Cells(
            listOf(
                Cell(), openedCell, notOpenedCell,
                Cell(), Cell(count = 1), Cell(true)
            ),
            3
        ).operation()
        operation.open(Position(1, 1))

        assertThat(openedCell.open).isTrue()
        assertThat(notOpenedCell.open).isFalse()
    }

    @Test
    fun `모두 열리면 종료응답을 받는다`() {
        val operation = Cells(listOf(Cell(), Cell(true)), 2).operation()
        operation.open(Position(1, 1))

        assertThat(operation.result).isEqualTo(Operation.Result.END)
    }

    @Test
    fun `영역을 벗어난곳을 열면 OUT_OF_MATRIX 응답을 받는다`() {
        val operation = Cells(listOf(Cell(), Cell(true)), 2).operation()
        operation.open(Position(10, 10))

        assertThat(operation.result).isEqualTo(Operation.Result.OUT_OF_MATRIX)
    }
}
