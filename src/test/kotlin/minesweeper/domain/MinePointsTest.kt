package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.comparables.shouldNotBeGreaterThan
import io.kotest.matchers.shouldBe

class MinePointsTest : StringSpec({

    "지뢰가 없는 경우 모든 점의 주변 지뢰 개수는 0이어야 한다." {
        val minePoints = MinePoints(emptySet())
        val testPoints = listOf(Point(0, 0), Point(0, 1), Point(1, 0), Point(1, 1))

        testPoints.forEach { point ->
            minePoints.countNeighborMines(point) shouldBe 0
        }
    }

    "지뢰가 하나만 있는 보드판에서 모든 칸의 지뢰 개수는 1을 넘지 않는다." {
        val minePoints = MinePoints(setOf(Point(1, 1)))
        val testPoints = listOf(Point(0, 0), Point(0, 1), Point(1, 0), Point(2, 2))

        testPoints.forEach { point ->
            minePoints.countNeighborMines(point) shouldNotBeGreaterThan 1
        }
    }
})
