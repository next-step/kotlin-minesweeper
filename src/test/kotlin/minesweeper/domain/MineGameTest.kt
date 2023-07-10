package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MineGameTest : StringSpec({
    "너비 5, 높이 10, 지뢰 개수 6을 전달하면 해당하는 지뢰 보드를 생성한다." {
        val width = PositiveNumber(5)
        val height = PositiveNumber(10)
        val mineCount = PositiveNumber(6)

        val mineGame = MineGame(RandomCellsGenerator)
        val board = mineGame.createBoard(width = width, height = height, mineCount = mineCount)

        board.board.size shouldBe height.value
        board.board[0].size shouldBe width.value
        board.board.flatten().count { it.hasMine } shouldBe mineCount.value
    }
})
