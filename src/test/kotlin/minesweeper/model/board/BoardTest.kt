package minesweeper.model.board

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.model.board.impl.EvenlyStrategy

class BoardTest : StringSpec({
    "입력한 수만큼 지뢰가 생성 되어야 한다" {
        val countOfMines = 22
        val board = Board(10, 10, EvenlyStrategy(countOfMines))

        board.countOfMine() shouldBe 22
    }

    "입력한 horizontal size 만큼의 지도가 생성 되어야 한다" {
        val horizontal = 17
        val board = Board(10, horizontal, EvenlyStrategy(5))

        board.horizontalSize shouldBe horizontal
    }

    "입력한 vertical size 만큼의 지도가 생성 되어야 한다" {
        val vertical = 13
        val board = Board(vertical, 10, EvenlyStrategy(5))

        board.verticalSize shouldBe vertical
    }
})
