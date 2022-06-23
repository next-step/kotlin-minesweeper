package minesweeper.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class MineBoardTest : DescribeSpec({

    describe("open") {
        context("좌표가 숫자필드 일 경우") {
            it("숫자필드와 인접한 숫자필드들도 모두 열린다") {
                /**
                 * * C *
                 * C C C
                 * * C C
                 */
                val mineBoard = mineBoard()
                mineBoard.open(Coordinate(0, 1))

                val openedCoordinates = mineBoard.boardFields.boardFields
                    .filter { it.isOpen }
                    .map { it.coordinate }
                openedCoordinates shouldContainAll listOf(
                    Coordinate(0, 1),
                    Coordinate(1, 0),
                    Coordinate(1, 1),
                    Coordinate(1, 2),
                    Coordinate(2, 1),
                    Coordinate(2, 2),
                )
            }
        }

        context("좌표가 지뢰필드 일 경우") {
            it("지뢰필드는 열리지만 인접한 필드들은 열리지 않는다") {
                /**
                 * * C *
                 * C C C
                 * * C C
                 */
                val mineBoard = mineBoard()
                mineBoard.open(Coordinate(0, 0))

                val openedCoordinates = mineBoard.boardFields.boardFields
                    .filter { it.isOpen }
                    .map { it.coordinate }
                openedCoordinates shouldContainExactly listOf(
                    Coordinate(0, 0)
                )
            }
        }
    }

    describe("isEnd") {
        context("지뢰필드를 오픈하지 않으면") {
            it("게임이 종료되지 않는다") {
                /**
                 * * C *
                 * C C C
                 * * C C
                 */
                val mineBoard = mineBoard()
                mineBoard.open(Coordinate(0, 1))

                mineBoard.isEnd shouldBe false
            }
        }

        context("지뢰필드를 오픈하면") {
            it("게임이 종료된다") {
                /**
                 * * C *
                 * C C C
                 * * C C
                 */
                val mineBoard = mineBoard()
                mineBoard.open(Coordinate(0, 0))

                mineBoard.isEnd shouldBe true
            }
        }
    }
})

private fun mineBoard(): MineBoard {
    /**
     * * C *
     * C C C
     * * C C
     */
    return MineBoard(
        BoardFields(
            listOf(
                BoardField.mine(Coordinate(0, 0)),
                BoardField.nonMine(Coordinate(0, 1)),
                BoardField.mine(Coordinate(0, 2)),
                BoardField.nonMine(Coordinate(1, 0)),
                BoardField.nonMine(Coordinate(1, 1)),
                BoardField.nonMine(Coordinate(1, 2)),
                BoardField.mine(Coordinate(2, 0)),
                BoardField.nonMine(Coordinate(2, 1)),
                BoardField.nonMine(Coordinate(2, 2)),
            )
        )
    )
}
