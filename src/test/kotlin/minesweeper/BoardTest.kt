package minesweeper

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith
import minesweeper.model.Board
import minesweeper.model.TestMinesStrategy
import minesweeper.view.ResultView

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
        board.getCells().cellList.filter { it.isMine }.size shouldBe 5
    }
    "지뢰찾기 보드 생성 테스트 예외 테스트" {
        val exception =
            shouldThrow<IllegalArgumentException> {
                Board(1, 1, 2)
            }
        exception.message should startWith("지뢰 개수는 셀의 개수보다 작아야합니다.")
    }
    "지뢰찾기 보드 생성 테스트 예외 높이 0 테스트" {
        val exception =
            shouldThrow<IllegalArgumentException> {
                Board(0, 1, 1)
            }
        exception.message should startWith("높이, 너비, 지뢰 개수는 0보다 커야합니다.")
    }
    "지뢰찾기 보드 생성 테스트 예외 너비 0 테스트" {
        val exception =
            shouldThrow<IllegalArgumentException> {
                Board(1, 0, 1)
            }
        exception.message should startWith("높이, 너비, 지뢰 개수는 0보다 커야합니다.")
    }
    "지뢰찾기 보드 생성 테스트 예외 지뢰 0 테스트" {
        val exception =
            shouldThrow<IllegalArgumentException> {
                Board(1, 1, 0)
            }
        exception.message should startWith("높이, 너비, 지뢰 개수는 0보다 커야합니다.")
    }
    "지뢰찾기 지뢰 오픈시 지뢰 확인 1테스트" {
        val board = Board(5, 5, 1)
        board.createCells(minesStrategy = testMinesStrategy)

        board.openCells(0, 0) shouldBe false
    }
    "지뢰찾기 숫자 오픈시 지뢰 확인 2 테스트" {
        val board = Board(5, 5, 1)
        board.createCells(minesStrategy = testMinesStrategy)

        board.openCells(0, 1) shouldBe true
    }
    "지뢰찾기 게임 종료 1 테스트" {
        val board = Board(5, 5, 1)
        board.createCells(minesStrategy = testMinesStrategy)
        board.addMineAroundCounts()

        board.openCells(4, 4)
        board.isFinishGame() shouldBe true
    }
    "지뢰찾기 게임 종료 2 테스트" {
        val board = Board(2, 6, 4)
        board.createCells(minesStrategy = testMinesStrategy)
        board.addMineAroundCounts()
        board.openCells(0, 1)

        board.isFinishGame() shouldBe false
    }
})
