package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class GridTest : StringSpec({
    "Grid 는 주어진 높이와 너비를 곱한 개수만큼 초기화 된다" {
        val dimension = Dimension(height = 10, width = 10)
        val mineCount = MineCount(10)
        val grid = Grid(dimension, mineCount)

        grid.cells.size shouldBe  10
        grid.cells[0].size shouldBe 10
    }

    "Grid 는 주어진 지뢰의 개수만큼 지뢰를 가진채로 초기화 된다" {
        val dimension = Dimension(height = 5, width = 5)
        val mineCount = MineCount(5)
        val grid = Grid(dimension, mineCount)

        val allCells = grid.cells.flatten()
        val mineCountInGrid = allCells.count { it.isMine() }

        mineCountInGrid shouldBe 5
    }

    "Grid 의 알맞은 위치에 지뢰가 생성된다" {
        val dimension = Dimension(height = 5, width = 5)
        val mineCount = MineCount(5)
        val minePositions = setOf(1, 2, 3, 4, 5)
        val mineGenerator = object : MineGenerator {
            override fun generateMinePositions(totalCells: Int, mineCount: Int): Set<Int> {
                return minePositions
            }
        }
        val grid = Grid(dimension, mineCount, mineGenerator)
        val allCells = grid.cells.flatten()
        allCells.take(5).all { it.isMine() } shouldBe true
    }
})
