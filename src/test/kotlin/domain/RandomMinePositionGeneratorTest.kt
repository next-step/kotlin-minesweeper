package domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class RandomMinePositionGeneratorTest : FunSpec({
    context("높이와 너비만큼의 게임판을 생성한다.") {
        val mineSweeperBoard = createTestBoard()
        mineSweeperBoard.width shouldBe 10
        mineSweeperBoard.height shouldBe 10
    }

    context("테스트 보드에 설치된 지뢰를 확인할 수 있다.") {
        val mineSweeperBoard = createTestBoard()
        mineSweeperBoard.isMine(Position(0, 0)) shouldBe false
    }

    context("테스트 보드의 특정 좌표 주변의 지뢰 개수 확인 테스트") {
        withData(
            Position(0, 0) to 0,
            Position(1, 0) to 1,
            Position(2, 0) to 2,
            Position(2, 0) to 2,
            Position(3, 1) to 3,
            Position(9, 9) to 0,
        ) { (position, expected) ->
            val board = createTestBoard()
            board.getMineCountAround(position) shouldBe expected
        }
    }
})
