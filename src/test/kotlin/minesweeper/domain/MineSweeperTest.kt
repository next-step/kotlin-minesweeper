package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class MineSweeperTest : DescribeSpec({

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

        context("게임이 종료된 경우") {
            it("IllegalStatementException 이 발생한다") {
                /**
                 * * C *
                 * C C C
                 * * C C
                 */
                val mineBoard = mineBoard()
                mineBoard.open(Coordinate(0, 0))

                shouldThrow<IllegalArgumentException> { mineBoard.open(Coordinate(1, 1)) }
            }
        }
    }

    describe("isEnd") {
        context("숫자필드를 모두 오픈하지 않거나 지뢰필드를 오픈하지 않으면") {
            it("게임이 종료되지 않는다") {
                /**
                 * * C *
                 * C * C
                 * * C C
                 */
                val mineSweeper = MineSweeper(
                    BoardFields(
                        listOf(
                            BoardField.mine(Coordinate(0, 0)),
                            BoardField.nonMine(Coordinate(0, 1)),
                            BoardField.mine(Coordinate(0, 2)),
                            BoardField.nonMine(Coordinate(1, 0)),
                            BoardField.mine(Coordinate(1, 1)),
                            BoardField.nonMine(Coordinate(1, 2)),
                            BoardField.mine(Coordinate(2, 0)),
                            BoardField.nonMine(Coordinate(2, 1)),
                            BoardField.nonMine(Coordinate(2, 2)),
                        )
                    )
                )

                mineSweeper.open(Coordinate(0, 1))

                mineSweeper.isEnd shouldBe false
            }
        }

        context("숫자필드를 모두 오픈하면") {
            it("게임이 종료된다") {
                /**
                 * * C *
                 * C C C
                 * * C C
                 */
                val mineBoard = mineBoard()
                mineBoard.open(Coordinate(0, 1))

                mineBoard.isEnd shouldBe true
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

    describe("isWin") {
        context("숫자필드를 모두 오픈 한 경우") {
            it("게임에서 승리한다") {
                /**
                 * * C *
                 * C C C
                 * * C C
                 */
                val mineBoard = mineBoard()
                mineBoard.open(Coordinate(0, 1))

                mineBoard.isWin shouldBe true
            }
        }

        context("지뢰필드를 오픈한 경우") {
            it("게임에서 패배한다") {
                /**
                 * * C *
                 * C C C
                 * * C C
                 */
                val mineBoard = mineBoard()
                mineBoard.open(Coordinate(0, 0))

                mineBoard.isWin shouldBe false
            }
        }
    }
})

private fun mineBoard(): MineSweeper {
    /**
     * * C *
     * C C C
     * * C C
     */
    return MineSweeper(
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
