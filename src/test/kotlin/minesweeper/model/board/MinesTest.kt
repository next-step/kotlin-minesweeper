package minesweeper.model.board

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.model.board.minedeploy.impl.SpecifiedCoordinatesStrategy
import minesweeper.model.point.Attribute
import minesweeper.model.point.CoordinateFixture.toCoordinate

class MinesTest : StringSpec({

    "지뢰의 갯수를 반환 하여야 한다" {
        val limit = (10 to 10).toBoardLimit()
        val mines = SpecifiedCoordinatesStrategy(
            0 to 0,
            0 to 1,
            1 to 1,
            2 to 2,
            3 to 3,
        ).deployPoints(limit).toMines(limit)

        mines.count shouldBe 5
    }

    "특정한 Coordinate 가 입력되면 해당 지역의 Attribute 를 반환해야 한다" {
        val limit = (10 to 10).toBoardLimit()
        val mines = SpecifiedCoordinatesStrategy(
            0 to 0,
            0 to 1,
            1 to 1,
            2 to 2,
            3 to 3,
        ).deployPoints(limit).toMines(limit)

        mines.attribute((0 to 1).toCoordinate()) shouldBe Attribute.MINE
        mines.attribute((1 to 0).toCoordinate()) shouldBe Attribute.GROUND
    }

    "주변 8개 지점에 매설된 지뢰의 숫자를 반환 해야 한다" {
        val limit = (10 to 10).toBoardLimit()
        val mines = SpecifiedCoordinatesStrategy(
            0 to 0,
            0 to 1,
            0 to 2,
            1 to 0,

            1 to 2,
            2 to 0,
            2 to 1,
            2 to 2,
        ).deployPoints(limit).toMines(limit)
        val actualAdjMineCount = mines.adjacentMineCount((1 to 1).toCoordinate())

        actualAdjMineCount shouldBe 8
    }
})
