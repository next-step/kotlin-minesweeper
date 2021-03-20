package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.random.Random

class MotherCellTest {
    @Test
    internal fun `높이 너비만큼 셀을 만든다`() {
        assertThat(MotherCells(10, 10).cells(0)).hasSize(100)
    }

    @Test
    internal fun `지뢰 개수만큼 생성한다`() {
        val cells: List<Cell> = MotherCells(10, 10).cells(bomb = 10)
        assertThat(cells.filter { it.bomb }).hasSize(10)
    }

    class MotherCells(private val width: Int, private val height: Int) {
        fun cells(bomb: Int): List<Cell> {
            val randoms = (1..(width * height)).map { Random.nextDouble() }
            val boundary = randoms.sorted().take(bomb + 1).last()
            return randoms.map { Cell(it < boundary) }
        }
    }

    class Cell(val bomb: Boolean)
}
