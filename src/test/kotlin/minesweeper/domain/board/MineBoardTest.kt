package minesweeper.domain.board

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.RandomMineGenerator

class MineBoardTest : FreeSpec({
    "지뢰찾기 보드를 생성하면" - {

        val height = 10
        val width = 10
        val mineCount = 10
        val mineGenerator = RandomMineGenerator

        val mineBoard = MineBoard(height, width, mineCount, mineGenerator)

        "지뢰 보드 높이를 가지고 있다" {
            mineBoard.height shouldBe 10
        }

        "지뢰 보드 너비를 가지고 있다" {
            mineBoard.width shouldBe 10
        }

        "지뢰 보드의 컴포넌트 개수는 높이*너비의 값과 동일하다" {
            mineBoard.components().size shouldBe 100
        }
    }
})