package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MotherCellTest {
    @Test
    internal fun `높이 너비만큼 셀을 만든다`() {
        assertThat(MotherCells(10, 10).cells).hasSize(100)
    }

    class MotherCells(width: Int, height: Int) {
        val cells: Cells = Cells(width, height)
    }

    class Cells(private val cells: List<Cell>) : List<Cell> by cells {
        constructor(width: Int, height: Int) : this(Cell() * (width * height))
    }

    class Cell() {
        operator fun times(number: Int): List<Cell> = (0 until number).map { Cell() }
    }
}
