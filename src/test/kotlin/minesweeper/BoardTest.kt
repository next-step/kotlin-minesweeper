package minesweeper

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith
import minesweeper.model.Board
import minesweeper.model.TestMinesStrategy

class BoardTest : StringSpec({
    val testMinesStrategy = TestMinesStrategy()

    "지뢰찾기 보드 생성 테스트 1" {
        val board = Board(3, 3, 1)
        board.createCells(minesStrategy = testMinesStrategy).size() shouldBe 9
    }
    "지뢰찾기 보드 생성 테스트 2" {
        val board = Board(4, 3, 1)
        board.createCells(minesStrategy = testMinesStrategy).size() shouldBe 12
    }
    "지뢰찾기 보드 생성 지뢰 개수 테스트 " {
        val board = Board(4, 3, 5).createCells(minesStrategy = testMinesStrategy)
        board.getCells().cellList.filter { it.isMine() }.size shouldBe 5
    }
    "지뢰찾기 보드 생성 테스트 예외 테스트" {
        val board = Board(1, 1, 2)
        val exception =
            shouldThrow<IllegalArgumentException> {
                board.createCells(minesStrategy = testMinesStrategy).size() shouldBe 12
            }
        exception.message should startWith("Failed requirement.")
    }
})
