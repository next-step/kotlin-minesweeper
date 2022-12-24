package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.board.MineBoard

class BoardTest : FreeSpec({
    "보드를 생성하기 위해서" - {

        val height = 10
        val width = 10
        val mineCount = 10

        "높이, 너비, 지뢰 개수 인자가 필요하다" {
            shouldNotThrowAny { MineBoard(height = height, width = width, mineCount = mineCount, mineGenerator = RandomMineGenerator) }
        }
    }

    "보드는" - {

        val height = 10
        val width = 10
        val mineCount = 10

        val board = MineBoard(height, width, mineCount, RandomMineGenerator)

        "높이를 확인할 수 있다" {
            board.height shouldBe height
        }

        "너비를 확인할 수 있다" {
            board.width shouldBe width
        }
    }
})
