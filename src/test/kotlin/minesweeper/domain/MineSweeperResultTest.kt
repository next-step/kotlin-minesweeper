package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.Coordinate
import minesweeper.Land

internal class MineSweeperResultTest : StringSpec({

    "지뢰판 속 열려있는 지뢰가 없을 경우 승리로 판단한다." {
        val cells = mapOf(
            Pair(Coordinate(0, 0), Mine(status = DotStatus.HIDDEN)),
            Pair(Coordinate(0, 1), Land(1)),
            Pair(Coordinate(0, 2), Land(0)),
            Pair(Coordinate(1, 0), Land(1)),
            Pair(Coordinate(1, 1), Land(1)),
            Pair(Coordinate(1, 2), Land(0)),
            Pair(Coordinate(2, 0), Land(0)),
            Pair(Coordinate(2, 1), Land(0)),
            Pair(Coordinate(2, 2), Land(0)),
        )

        val mineBoard = MineBoard(cells = cells)

        MineSweeperResult.isWin(mineBoard) shouldBe true
    }

    "지뢰판 속 열려있는 지뢰가 있을 경우 패배로 판단한다." {
        val cells = mapOf(
            Pair(Coordinate(0, 0), Mine(status = DotStatus.OPEN)),
            Pair(Coordinate(0, 1), Land(1)),
            Pair(Coordinate(0, 2), Land(0)),
            Pair(Coordinate(1, 0), Land(1)),
            Pair(Coordinate(1, 1), Land(1)),
            Pair(Coordinate(1, 2), Land(0)),
            Pair(Coordinate(2, 0), Land(0)),
            Pair(Coordinate(2, 1), Land(0)),
            Pair(Coordinate(2, 2), Land(0)),
        )

        val mineBoard = MineBoard(cells = cells)

        MineSweeperResult.isWin(mineBoard) shouldBe false
    }
})
