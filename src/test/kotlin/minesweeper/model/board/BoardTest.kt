package minesweeper.model.board

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.app.GameStatus
import minesweeper.model.board.minedeploy.impl.SpecifiedCoordinatesStrategy
import minesweeper.model.point.CoordinateFixture.toCoordinate

class BoardTest : StringSpec({

    "입력한 수만큼 지뢰가 생성 되어야 한다" {
        val countOfMines = 22
        val board = Board(
            mineCount = countOfMines,
            limit = (10 to 10).toBoardLimit(),
        )

        board.minesCount shouldBe 22
    }

    "입력한 horizontal size 만큼의 지도가 생성 되어야 한다" {
        val horizontal = 17
        val board = Board(
            mineCount = 5,
            limit = (10 to horizontal).toBoardLimit(),
        )

        board.limit.horizontalLimit.value shouldBe horizontal
    }

    "입력한 vertical size 만큼의 지도가 생성 되어야 한다" {
        val vertical = 13
        val board = Board(
            mineCount = 1,
            limit = (vertical to 10).toBoardLimit(),
        )

        board.limit.verticalLimit.value shouldBe vertical
    }

    "지뢰가 매설된 지역을 tryOpen 하면 LOSE 를 반환해야 한다" {
        val limit = (4 to 4).toBoardLimit()
        val board = Board(
            mines = SpecifiedCoordinatesStrategy(
                1 to 1,
            ).deployPoints(limit).toMines(limit),
            limit = limit
        )
        val actualStatus1 = board.tryOpen((0 to 1).toCoordinate())
        val actualStatus2 = board.tryOpen((1 to 1).toCoordinate())

        actualStatus1 shouldBe GameStatus.RUNNING
        actualStatus2 shouldBe GameStatus.LOSE
    }
})
