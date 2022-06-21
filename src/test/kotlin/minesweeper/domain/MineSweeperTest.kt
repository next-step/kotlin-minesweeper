package minesweeper.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class MineSweeperTest : DescribeSpec({

    describe("createBoard") {
        context("높이, 너비, 지뢰 개수가 주어졌을 때") {
            val mineCoordinates = listOf(
                Coordinate(CoordinateIndex(0), CoordinateIndex(0)),
                Coordinate(CoordinateIndex(1), CoordinateIndex(1)),
            )
            val mineSweeper = MineSweeper() { _, _ -> mineCoordinates }
            val mineBoard = mineSweeper.mineBoard(2, 2, 2)

            it("지뢰 판을 생성한다") {
                mineBoard shouldNotBe null
            }

            it("지뢰 판의 필드 개수는 높이 * 너비 이다") {
                mineBoard.boardFields.size shouldBe 4
            }

            it("배열된 지뢰를 확인할 수 있다") {
                mineBoard.boardFields.filter { it.isMine } shouldContainAll listOf(
                    BoardField(Coordinate(CoordinateIndex(0), CoordinateIndex(0)), true),
                    BoardField(Coordinate(CoordinateIndex(1), CoordinateIndex(1)), true),
                )
            }

            it("지뢰가 아닌 필드를 확인할 수 있다") {
                mineBoard.boardFields.filterNot { it.isMine } shouldContainAll listOf(
                    BoardField(Coordinate(CoordinateIndex(0), CoordinateIndex(1)), false),
                    BoardField(Coordinate(CoordinateIndex(1), CoordinateIndex(0)), false),
                )
            }
        }
    }
})
