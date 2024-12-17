package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import org.junit.jupiter.api.Assertions.assertEquals

class GridTest : StringSpec({
    "Grid 는 주어진 높이와 너비를 곱한 개수만큼 초기화 된다" {
        val dimension = Dimension(height = 10, width = 10)
        val mineCount = MineCount(10)
        val grid = Grid(dimension, mineCount)

        assertEquals(10, grid.cells.size)
        assertEquals(10, grid.cells[0].size)
    }

    "Grid 는 주어진 지뢰의 개수만큼 지뢰를 가진채로 초기화 된다" {
        val dimension = Dimension(height = 5, width = 5)
        val mineCount = MineCount(5)
        val grid = Grid(dimension, mineCount)

        val allCells = grid.cells.flatten()
        val mineCountInGrid = allCells.count { it.isMine() }

        assertEquals(5, mineCountInGrid)
    }
})
