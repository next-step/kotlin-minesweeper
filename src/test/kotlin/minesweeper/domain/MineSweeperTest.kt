package minesweeper.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class MineSweeperTest : DescribeSpec({

    describe("createBoard") {
        context("높이, 너비, 지뢰 개수가 주어졌을 때") {
            val mineBoard = MineSweeper().mineBoard(2, 2, 2)

            it("지뢰 판을 생성한다") {
                mineBoard shouldNotBe null
            }

            it("지뢰 판의 필드 개수는 높이 * 너비 이다") {
                mineBoard.boardFields.size shouldBe 4
            }

            it("지뢰 판의 지뢰 개수는 주어진 지뢰 개수이다") {
                mineBoard.boardFields.count { it.isMine } shouldBe 2
            }
        }
    }
})
