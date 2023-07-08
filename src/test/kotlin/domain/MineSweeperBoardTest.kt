package domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MineSweeperBoardTest : FunSpec({
    context("높이와 너비만큼의 게임판을 생성한다.") {
        val board = MineSweeperBoard(BoardSize(10, 15), 10)
        board.boardSize.width shouldBe 10
        board.boardSize.height shouldBe 15
    }
})
