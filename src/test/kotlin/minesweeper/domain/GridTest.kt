package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class GridTest : StringSpec({
    "Grid 는 주어진 높이와 너비를 곱한 개수만큼 초기화 된다" {
        val dimension = Dimension(height = 10, width = 10)
        val mineCount = MineCount(10)
        val grid = Grid(dimension, mineCount, RandomMineGenerator())

        grid.cells.size shouldBe 10
        grid.cells[0].size shouldBe 10
    }

    "Grid 는 주어진 지뢰의 개수만큼 지뢰를 가진채로 초기화 된다" {
        val dimension = Dimension(height = 5, width = 5)
        val mineCount = MineCount(5)
        val grid = Grid(dimension, mineCount, RandomMineGenerator())

        val allCells = grid.cells.flatten()
        val mineCountInGrid = allCells.count { it.isMine() }

        mineCountInGrid shouldBe 5
    }

    "Grid 의 알맞은 위치에 지뢰가 생성된다" {
        val dimension = Dimension(height = 5, width = 5)
        val mineCount = MineCount(5)
        val minePositions = setOf(0, 1, 2, 3, 4)
        val mineGenerator = MineGenerator { _, _ -> minePositions }
        val grid = Grid(dimension, mineCount, mineGenerator)
        val allCells = grid.cells.flatten()
        allCells.take(5).all { it.isMine() } shouldBe true
    }

    "Grid 의 0,0 이 지뢰라면, (0,1) (1,1), (1,0) 은 인접한 지뢰가 1이다" {
        val dimension = Dimension(height = 5, width = 5)
        val mineCount = MineCount(1)
        val minePosition = setOf(0)
        val mineGenerator = MineGenerator { _, _ -> minePosition }

        val grid = Grid(dimension, mineCount, mineGenerator)
        grid.cells[0][1].displayString() shouldBe "1"
        grid.cells[1][1].displayString() shouldBe "1"
        grid.cells[1][0].displayString() shouldBe "1"
    }
})
