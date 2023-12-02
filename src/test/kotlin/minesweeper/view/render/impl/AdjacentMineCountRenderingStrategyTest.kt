package minesweeper.view.render.impl

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.model.board.Board
import minesweeper.model.board.toBoardLimit
import minesweeper.model.point.TileType
import minesweeper.model.point.CoordinateFixture.toCoordinate
import minesweeper.model.point.PointsFixture

class AdjacentMineCountRenderingStrategyTest : StringSpec({

    "지뢰가 아닌경우, 주변에 존재하는 지뢰의 갯수로 표현 해야 한다" {
        val board = Board(
            points = PointsFixture.make(
                0 to 0,
                1 to 1,
                2 to 2,
                3 to 3
            ),
            limit = (4 to 4).toBoardLimit(),
        )
        val strategy = AdjacentMineCountRenderingStrategy(board)
        val actual = strategy.symbol(TileType.NONE, (0 to 1).toCoordinate())
        actual shouldBe "2"
    }
})
