package minesweeper.view

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.model.board.Board
import minesweeper.model.board.minedeploy.impl.SpecifiedCoordinatesStrategy
import minesweeper.model.board.toBoardLimit
import minesweeper.view.render.impl.AdjacentMineCountRenderingStrategy

class OutputViewTest : StringSpec({

    "MinMap 그리기가 잘 표현되는지 검증한다" {
        val limit = (4 to 4).toBoardLimit()
        val board = Board(
            mines = SpecifiedCoordinatesStrategy(
                0 to 0,
                1 to 1,
                2 to 2,
                3 to 3
            ).deployPoints(limit),
            limit = limit,
        )
        OutputView().renderingBoard(board) shouldBe """
            * C C C
            C * C C
            C C * C
            C C C *
        """.trimIndent()
    }

    "자신을 제외한 주변 8개 지점에 포함된 지뢰의 개수가 표시되어야 한다" {
        val limit = (4 to 4).toBoardLimit()
        val board = Board(
            mines = SpecifiedCoordinatesStrategy(
                0 to 0,
                1 to 1,
                2 to 2,
                3 to 3
            ).deployPoints(limit),
            limit = limit,
        )
        OutputView(AdjacentMineCountRenderingStrategy).renderingBoard(board) shouldBe """
            * 2 1 0
            2 * 2 1
            1 2 * 2
            0 1 2 *
        """.trimIndent()
    }
})
