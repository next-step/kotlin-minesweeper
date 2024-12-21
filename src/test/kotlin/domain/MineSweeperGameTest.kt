package domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class MineSweeperGameTest : DescribeSpec({
    lateinit var mineGameMetric: MineGameMetric
    lateinit var mineBoard: MineBoard
    lateinit var sut: MineSweeperGame

    describe("isContinueGame test") {
        context("하나 이상의 셀을 지뢰 open 한 경우") {
            beforeTest {
                mineGameMetric = MineGameMetric(3, 3, 8)
                val cells =
                    Cells(
                        listOf(
                            Cell.MineCell(Coordinate(1, 1), CellStatus.OPEN),
                            Cell.MineCell(Coordinate(2, 2)),
                            Cell.MineCell(Coordinate(3, 3)),
                        ),
                    )

                mineBoard = MineBoard(mineGameMetric, cells)
                sut = MineSweeperGame(mineBoard)
            }

            it("should be false") {
                sut.isContinueGame().shouldBeFalse()
            }

            it("result should be FAILURE") {
                sut.result shouldBe GameResult.FAILURE
            }
        }

        context("비어있는 모든 셀을 open 한 경우") {
            beforeTest {
                mineGameMetric = MineGameMetric(3, 3, 8)
                val cells =
                    Cells(
                        listOf(
                            Cell.EmptyCell(Coordinate(1, 1), CellStatus.OPEN),
                            Cell.EmptyCell(Coordinate(2, 2), CellStatus.OPEN),
                            Cell.EmptyCell(Coordinate(3, 3), CellStatus.OPEN),
                        ),
                    )

                mineBoard = MineBoard(mineGameMetric, cells)
                sut = MineSweeperGame(mineBoard)
            }

            it("should be false") {
                sut.isContinueGame().shouldBeFalse()
            }

            it("game result should be success") {
                sut.isContinueGame()
                sut.result shouldBe GameResult.SUCCESS
            }
        }

        context("지뢰 셀을 open 하지 않았고, 비어있는 모든 칸을 open 하지 않은 경우") {
            beforeTest {
                mineGameMetric = MineGameMetric(3, 3, 8)
                val cells =
                    Cells(
                        listOf(
                            Cell.EmptyCell(Coordinate(1, 1), CellStatus.OPEN),
                            Cell.EmptyCell(Coordinate(2, 2), CellStatus.OPEN),
                            Cell.EmptyCell(Coordinate(3, 3), CellStatus.CLOSED),
                            Cell.MineCell(Coordinate(3, 3), CellStatus.CLOSED),
                            Cell.MineCell(Coordinate(3, 3), CellStatus.CLOSED),
                            Cell.MineCell(Coordinate(3, 3), CellStatus.CLOSED),
                        ),
                    )

                mineBoard = MineBoard(mineGameMetric, cells)
                sut = MineSweeperGame(mineBoard)
            }

            it("should be true") {
                sut.isContinueGame().shouldBeTrue()
            }
        }
    }
})
