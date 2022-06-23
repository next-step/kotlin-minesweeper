package minesweeper.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class MineSweeperFactoryTest : DescribeSpec({

    describe("mineSweeper") {
        context("높이, 너비, 지뢰 개수가 주어졌을 때") {
            val mineCoordinates = listOf(
                Coordinate(0, 0),
                Coordinate(1, 1),
            )
            val mineSweeperFactory = MineSweeperFactory { _, _ -> mineCoordinates }
            val mineBoard = mineSweeperFactory.mineSweeper(2, 2, 2)

            it("지뢰 판을 생성한다") {
                mineBoard shouldNotBe null
            }

            it("지뢰 판의 필드 개수는 높이 * 너비 이다") {
                mineBoard.boardFields.boardFields.size shouldBe 4
            }

            it("배열된 지뢰를 확인할 수 있다") {
                mineBoard.boardFields.boardFields.filterIsInstance<MineField>() shouldContainAll listOf(
                    BoardField.mine(Coordinate(0, 0)),
                    BoardField.mine(Coordinate(1, 1)),
                )
            }

            it("지뢰가 아닌 필드를 확인할 수 있다") {
                mineBoard.boardFields.boardFields.filterNot { it is MineField } shouldContainAll listOf(
                    BoardField.nonMine(Coordinate(0, 1)),
                    BoardField.nonMine(Coordinate(1, 0)),
                )
            }
        }
    }
})
