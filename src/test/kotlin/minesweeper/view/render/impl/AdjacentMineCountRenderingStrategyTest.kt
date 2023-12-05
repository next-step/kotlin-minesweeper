package minesweeper.view.render.impl

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.model.board.Board
import minesweeper.model.board.minedeploy.impl.SpecifiedCoordinatesStrategy
import minesweeper.model.board.toBoardLimit
import minesweeper.model.board.toMines
import minesweeper.model.point.CoordinateFixture.toCoordinate

class AdjacentMineCountRenderingStrategyTest : StringSpec({

    "지뢰가 아닌경우, 주변에 존재하는 지뢰의 갯수로 표현 해야 한다" {
        val limit = (4 to 4).toBoardLimit()
        val board = Board(
            mines = SpecifiedCoordinatesStrategy(
                0 to 0,
                1 to 1,
                2 to 2,
                3 to 3
            ).deployPoints(limit).toMines(limit),
            limit = limit
        )
        val actual = AdjacentMineCountRenderingStrategy.symbolOf(board, (0 to 1).toCoordinate())

        actual shouldBe "2"
    }
})
