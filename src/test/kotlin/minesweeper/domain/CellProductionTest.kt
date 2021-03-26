package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CellProductionTest {
    @Test
    internal fun `높이 너비만큼 셀을 만든다`() {
        assertThat(CellProduction(10, 10).cells(1)).hasSize(100)
    }

    @Test
    internal fun `지뢰 개수만큼 생성한다`() {
        val cells: List<Cell> = CellProduction(10, 10).cells(bomb = 10)
        assertThat(cells.filter { it.bomb }).hasSize(10)
    }

    @Test
    internal fun `지뢰는 최소 1개이상이어야 한다`() {
        assertThrows<IllegalArgumentException> { CellProduction(10, 10).cells(bomb = 0) }
    }

    @Test
    internal fun `너비와 높이는 1 이상이어야 한다`() {
        assertThrows<IllegalArgumentException> { CellProduction(0, 10).cells(bomb = 0) }
    }

    @Test
    internal fun `지뢰수는 전체 셀수보다 작아야 한다`() {
        assertThrows<IllegalArgumentException> { CellProduction(2, 2).cells(bomb = 4) }
    }

    @Test
    internal fun `셀 생성을 CellSource 에게 위임한다`() {
        val cells = listOf(Cell(), Cell(), Cell(), Cell(true))
        val width = 2
        val motherCells = CellProduction(
            width, cells.size / width,
            object : Budding {
                override fun cells(bomb: Int, matrix: Matrix): Cells {
                    return Cells(cells, matrix)
                }
            }
        )
        assertThat(motherCells.cells(1)).isEqualTo(Cells(cells, width))
    }

    /**
     * 0, 1, 💣, 💣
     * 0, 1, 2, 2
     * 0, 0, 0, 0
     */
    @Test
    fun `옆 셀의 지뢰수가 기록되어 있다`() {
        val cells = listOf(
            Cell(), Cell(count = 1), Cell(bomb = true), Cell(bomb = true),
            Cell(), Cell(count = 1), Cell(count = 2), Cell(count = 2),
            Cell(), Cell(count = 0), Cell(count = 0), Cell(count = 0)
        )
        val width = 4
        val motherCells = CellProduction(
            width, cells.size / width,
            Budding.Default(
                RandomDoubles(
                    listOf(
                        1, 1, 0, 0,
                        1, 1, 1, 1,
                        1, 1, 1, 1
                    ).map { it.toDouble() } // 작은수가 지뢰
                )
            )
        )

        assertThat(motherCells.cells(2)).isEqualTo(Cells(cells, width))
    }
}
