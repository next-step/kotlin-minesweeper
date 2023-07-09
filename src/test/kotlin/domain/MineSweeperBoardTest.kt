package domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MineSweeperBoardTest : FunSpec({
    context("보드에 지뢰를 설치할 수 있다.") {
        val mineSweeperBoard = MineSweeperBoard(BoardSize(10, 10))
        mineSweeperBoard.putMine(Position(0, 0))

        mineSweeperBoard.isMine(Position(0, 0)) shouldBe true
    }

    context("테스트 보드의 0x0 좌표 주변의 지뢰는 0개이다.") {
        val board = createTestBoard()
        board.getMineCountAround(Position(0, 0)) shouldBe 0
    }
})
