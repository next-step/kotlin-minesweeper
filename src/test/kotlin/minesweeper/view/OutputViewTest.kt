package minesweeper.view

import io.kotest.core.spec.style.StringSpec
import minesweeper.model.board.Board
import minesweeper.model.point.PointsFixture

class OutputViewTest : StringSpec({
    "MinMap 그리기가 잘 표현되는지 검증한다" {
        val board = Board(
            PointsFixture.make(
                0 to 0,
                1 to 1,
                2 to 2,
                3 to 3
            ),
            10,
            10
        )
        val renderingBoard = OutputView.renderingBoard(board)
        println(renderingBoard)
    }

})
