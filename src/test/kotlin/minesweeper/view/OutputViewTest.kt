package minesweeper.view

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.model.board.Board
import minesweeper.model.board.toBoardLimit
import minesweeper.model.point.PointsFixture
import minesweeper.view.render.impl.AdjacentMineCountRenderingStrategy

class OutputViewTest : StringSpec({

    "MinMap 그리기가 잘 표현되는지 검증한다" {
        val board = Board(
            points = PointsFixture.make(
                0 to 0,
                1 to 1,
                2 to 2,
                3 to 3
            ),
            limit = (4 to 4).toBoardLimit(),
        )
        OutputView().renderingBoard(board) shouldBe """
            * C C C
            C * C C
            C C * C
            C C C *
        """.trimIndent()
    }

    "자신을 제외한 주변 8개 지점에 포함된 지뢰의 개수가 표시되어야 한다" {
        val board = Board(
            points = PointsFixture.make(
                0 to 0,
                1 to 1,
                2 to 2,
                3 to 3
            ),
            limit = (4 to 4).toBoardLimit(),
        )
        OutputView(AdjacentMineCountRenderingStrategy(board)).renderingBoard(board) shouldBe """
            * 2 1 0
            2 * 2 1
            1 2 * 2
            0 1 2 *
        """.trimIndent()
    }
})
