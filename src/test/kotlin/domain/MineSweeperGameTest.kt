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

    describe("openAdjacentCell") {
        context("연결되어 있는 셀이 없는 경우") {
            it("해당 셀만 오픈한다.") {
                mineGameMetric = MineGameMetric(3, 3, 8)
                val cellList =
                    listOf(
                        Cell.EmptyCell(Coordinate(1, 1), CellStatus.CLOSED),
                        Cell.MineCell(Coordinate(1, 2), CellStatus.CLOSED),
                        Cell.MineCell(Coordinate(2, 1), CellStatus.CLOSED),
                        Cell.MineCell(Coordinate(2, 2), CellStatus.CLOSED),
                    )
                val cells = Cells(cellList)
                mineBoard = MineBoard(mineGameMetric, cells)
                sut = MineSweeperGame(mineBoard)
                sut.openAdjacentCell(Coordinate(1, 1))
                cellList[0].status shouldBe CellStatus.OPEN
                cellList[1].status shouldBe CellStatus.CLOSED
                cellList[2].status shouldBe CellStatus.CLOSED
                cellList[3].status shouldBe CellStatus.CLOSED
            }
        }

        context("연결되어 있는 셀이 있는 경우") {
            it("좌표와 연결된 비어있는 셀을 모두 오픈한다.") {
                mineGameMetric = MineGameMetric(3, 3, 8)
                val cellList =
                    listOf(
                        Cell.EmptyCell(Coordinate(1, 1), CellStatus.CLOSED),
                        Cell.EmptyCell(Coordinate(1, 2), CellStatus.CLOSED),
                        Cell.EmptyCell(Coordinate(1, 3), CellStatus.CLOSED),
                        Cell.EmptyCell(Coordinate(2, 1), CellStatus.CLOSED),
                        Cell.EmptyCell(Coordinate(2, 2), CellStatus.CLOSED),
                        Cell.EmptyCell(Coordinate(2, 3), CellStatus.CLOSED),
                        Cell.EmptyCell(Coordinate(3, 1), CellStatus.CLOSED),
                        Cell.EmptyCell(Coordinate(3, 2), CellStatus.CLOSED),
                        Cell.MineCell(Coordinate(3, 3), CellStatus.CLOSED),
                    )
                val cells = Cells(cellList)
                mineBoard = MineBoard(mineGameMetric, cells)
                sut = MineSweeperGame(mineBoard)

                sut.openAdjacentCell(Coordinate(1, 1))
                cellList[0].status shouldBe CellStatus.OPEN
                cellList[1].status shouldBe CellStatus.OPEN
                cellList[2].status shouldBe CellStatus.OPEN
                cellList[3].status shouldBe CellStatus.OPEN
                cellList[4].status shouldBe CellStatus.OPEN
                cellList[5].status shouldBe CellStatus.OPEN
                cellList[6].status shouldBe CellStatus.OPEN
                cellList[7].status shouldBe CellStatus.OPEN
                cellList[8].status shouldBe CellStatus.CLOSED
            }
        }
    }
})
