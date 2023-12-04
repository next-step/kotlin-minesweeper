package minesweeper.view.render.impl

import io.kotest.core.spec.style.StringSpec
import minesweeper.model.board.Board
import minesweeper.model.board.impl.SpecifiedCoordinatesStrategy
import minesweeper.model.board.toBoardLimit
import minesweeper.model.point.CoordinateFixture.toCoordinate
import minesweeper.model.vison.impl.VisionCoveredStrategy

class ExploringDefaultClosedAreaRenderingStrategyTest : StringSpec({

    "한다" {
        val limit = (4 to 4).toBoardLimit()
        val board = Board(
            mines = SpecifiedCoordinatesStrategy(
                0 to 0,
                1 to 1,
                2 to 2,
                3 to 3,
            ).deployPoints(limit),
            vision = VisionCoveredStrategy.coordinates(limit),
            limit = limit,
        )
        val symbol = ExploringDefaultClosedAreaRenderingStrategy.symbolOf(
            board,
            (0 to 0).toCoordinate()
        )
        print(symbol)
    }
})
