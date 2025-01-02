package domain

import domain.strategy.RandomMineCellGenerator
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeSameInstanceAs

class MineBoardTest : DescribeSpec({
    describe("`MineSweeperMetric`으로 `MineBoard`를 생성한다.") {
        lateinit var mineSweeperMetric: MineSweeperMetric
        beforeTest { mineSweeperMetric = MineSweeperMetric(3, 3, 1) }

        context("row x col = cell.size()") {
            it("should be true") {
                val cells = Cells.of(RandomMineCellGenerator(mineSweeperMetric))
                val sut = MineBoard(cells)
                sut.cellsSize() shouldBe 9
            }
        }
    }

    describe("hasMineExploded Test") {
        context("하나 이상의 MineCell이 Open 되어 있는 경우") {
            lateinit var sut: MineBoard
            beforeTest {
                val cells =
                    Cells(
                        listOf(
                            Cell.MineCell(Coordinate(1, 1), CellStatus.OPEN),
                            Cell.MineCell(Coordinate(2, 2), CellStatus.CLOSED),
                        ),
                    )
                sut = MineBoard(cells)
            }

            it("should be true") {
                sut.hasMineExploded().shouldBeTrue()
            }
        }

        context("모든 MineCell이 Closed 되어 있는 경우") {
            lateinit var sut: MineBoard
            beforeTest {
                val cells =
                    Cells(
                        listOf(
                            Cell.MineCell(Coordinate(1, 1)),
                            Cell.MineCell(Coordinate(2, 2)),
                            Cell.EmptyCell(Coordinate(3, 3)),
                        ),
                    )
                sut = MineBoard(cells)
            }

            it("should be false") {
                sut.hasMineExploded()
                    .shouldBeFalse()
            }
        }
    }

    describe("isCleared test") {
        lateinit var sut: MineBoard
        context("비어있는 모든 셀이 열려있는 경우") {
            beforeTest {
                val cells =
                    Cells(
                        listOf(
                            Cell.MineCell(Coordinate(1, 1)),
                            Cell.EmptyCell(Coordinate(2, 2), CellStatus.OPEN),
                            Cell.EmptyCell(Coordinate(3, 3), CellStatus.OPEN),
                        ),
                    )
                sut = MineBoard(cells)
            }

            it("should be true") {
                sut.isCleared() shouldBe true
            }
        }

        context("비어있는 셀이 하나 이상 closed 인 경우") {
            beforeTest {
                val cells =
                    Cells(
                        listOf(
                            Cell.MineCell(Coordinate(1, 1)),
                            Cell.EmptyCell(Coordinate(2, 2), CellStatus.CLOSED),
                            Cell.EmptyCell(Coordinate(3, 3), CellStatus.OPEN),
                        ),
                    )
                sut = MineBoard(cells)
            }

            it("should be false") {
                sut.isCleared() shouldBe false
            }
        }
    }

    describe("openCell test.") {
        lateinit var cellList: List<Cell>
        lateinit var sut: MineBoard

        beforeTest {
            cellList =
                listOf(
                    Cell.MineCell(
                        Coordinate(1, 1),
                    ),
                    Cell.EmptyCell(
                        Coordinate(1, 2),
                    ),
                )
            sut = MineBoard(Cells(cellList))
        }

        it("좌표의 셀을 open 한다.") {
            sut.openCell(Coordinate(1, 1))
            cellList[0].status shouldBe CellStatus.OPEN
        }
    }

    describe("getCell test") {
        lateinit var cellList: List<Cell>
        lateinit var sut: MineBoard

        beforeTest {
            cellList =
                listOf(
                    Cell.MineCell(
                        Coordinate(1, 1),
                    ),
                    Cell.EmptyCell(
                        Coordinate(1, 2),
                    ),
                )
            sut = MineBoard(Cells(cellList))
        }

        it("좌표에 해당하는 셀을 조회한다.") {
            val actual = sut.getCell(Coordinate(1, 1))
            actual shouldBeSameInstanceAs cellList[0]
        }
    }

    describe("getAdjacentCoordinates test") {
        it("인접한 셀의 좌표를 리턴한다.") {
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
            val sut = MineBoard(cells)
            val actual = sut.getAdjacentCoordinates(cellList[0])
            actual.size shouldBe 3
        }
    }
})
