package domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class MineSweeperGameTest : DescribeSpec({
    describe("isContinueGame Test") {
        lateinit var sut: MineSweeperGame

        context("하나 이상의 MineCell이 Open 되어 있는 경우") {
            beforeTest {
                val cells =
                    Cells(
                        listOf(
                            Cell.MineCell(Coordinate(1, 1)),
                            Cell.MineCell(Coordinate(2, 2)),
                            Cell.MineCell(Coordinate(3, 3), CellStatus.OPEN),
                        ),
                    )
                val mineBoard = MineBoard(cells)
                sut = MineSweeperGame(mineBoard)
            }

            it("should be false") {
                sut.isContinueGame() shouldBe false
            }
        }

        context("모든 MineCell이 Closed 상태인 경우") {
            val cells =
                Cells(
                    listOf(
                        Cell.MineCell(Coordinate(1, 1)),
                        Cell.MineCell(Coordinate(2, 2)),
                        Cell.MineCell(Coordinate(3, 3)),
                        Cell.EmptyCell(Coordinate(3, 3)),
                    ),
                )
            beforeTest {
                val mineBoard = MineBoard(cells)
                sut = MineSweeperGame(mineBoard)
            }

            it("should be true") {
                sut.isContinueGame() shouldBe true
            }
        }

        context("모든 Empty Cell이 Open인 경우") {
            beforeTest {
                val cells =
                    Cells(
                        listOf(
                            Cell.EmptyCell(Coordinate(1, 1), CellStatus.OPEN),
                            Cell.EmptyCell(Coordinate(2, 2), CellStatus.OPEN),
                            Cell.MineCell(Coordinate(3, 3)),
                            Cell.EmptyCell(Coordinate(4, 4), CellStatus.OPEN),
                        ),
                    )
                val mineBoard = MineBoard(cells)
                sut = MineSweeperGame(mineBoard)
            }

            it("should be false") {
                sut.isContinueGame() shouldBe false
            }
        }
    }

    describe("openAdjacentCell test") {
        lateinit var mineBoard: MineBoard
        lateinit var sut: MineSweeperGame

        context("연결되어 있는 셀이 없는 경우") {
            it("해당 셀만 오픈한다.") {
                val cellList =
                    listOf(
                        Cell.EmptyCell(Coordinate(1, 1), CellStatus.CLOSED),
                        Cell.MineCell(Coordinate(1, 2), CellStatus.CLOSED),
                        Cell.MineCell(Coordinate(2, 1), CellStatus.CLOSED),
                        Cell.MineCell(Coordinate(2, 2), CellStatus.CLOSED),
                    )
                val cells = Cells(cellList)
                mineBoard = MineBoard(cells)
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
                mineBoard = MineBoard(cells)
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

    describe("getGameResult test") {
        lateinit var mineBoard: MineBoard
        lateinit var sut: MineSweeperGame

        context("빈 셀이 모두 open된 경우") {
            it("should be SUCCESS") {
                val cellList =
                    listOf(
                        Cell.EmptyCell(Coordinate(1, 1), CellStatus.OPEN),
                        Cell.EmptyCell(Coordinate(1, 2), CellStatus.OPEN),
                        Cell.EmptyCell(Coordinate(1, 3), CellStatus.OPEN),
                        Cell.EmptyCell(Coordinate(2, 1), CellStatus.OPEN),
                        Cell.EmptyCell(Coordinate(2, 2), CellStatus.OPEN),
                        Cell.EmptyCell(Coordinate(2, 3), CellStatus.OPEN),
                        Cell.MineCell(Coordinate(3, 1), CellStatus.CLOSED),
                        Cell.MineCell(Coordinate(3, 2), CellStatus.CLOSED),
                        Cell.MineCell(Coordinate(3, 3), CellStatus.CLOSED),
                    )
                val cells = Cells(cellList)

                mineBoard = MineBoard(cells)
                sut = MineSweeperGame(mineBoard)
                val actual = sut.getGameResult()
                actual shouldBe GameResult.SUCCESS
            }
        }
    }
})
