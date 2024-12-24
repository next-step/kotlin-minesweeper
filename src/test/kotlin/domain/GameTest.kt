package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class GameTest : StringSpec({
    "게임 생성 시 초기 상태가 올바르게 설정된다." {
        val height = 5
        val width = 5
        val mineCount = 5

        val game = Game(height, width, mineCount)
        val state = game.getMineFieldState()

        state.cells.size shouldBe 5
        state.cells[0].size shouldBe 5
    }

    "지뢰가 없는 셀을 열면 셀이 열리고 인접한 빈 셀도 열린다." {
        val height = 3
        val width = 3
        val minePositions = setOf(Position(0, 0), Position(2, 2))
        val game = Game(height, width, minePositions.size)

        val result = game.openCell(1, 1)

        result shouldBe true
        val state = game.getMineFieldState()

        state.cells[1][1].isOpen shouldBe true
        state.cells[0][0].isOpen shouldBe false
    }

    "지뢰가 있는 셀을 열면 게임이 종료된다." {
        val height = 3
        val width = 3
        val minePositions = setOf(Position(0, 0))

        val game = Game(height, width, minePositions.size)
        val result = game.openCell(0, 0)

        result shouldBe false
    }

    "모든 지뢰가 아닌 셀이 열리면 승리 조건이 충족된다." {
        val height = 3
        val width = 3
        val minePositions = setOf(Position(0, 0), Position(2, 2))
        val game = Game(height, width, minePositions.size)

        game.openCell(1, 1)
        game.openCell(0, 1)
        game.openCell(1, 0)
        game.openCell(1, 2)
        game.openCell(2, 1)

        game.isWin() shouldBe true
    }
})
