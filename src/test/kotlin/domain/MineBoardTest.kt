package domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class MineBoardTest : DescribeSpec({
    lateinit var mineGameMetric: MineGameMetric
    lateinit var sut: MineBoard

    beforeTest {
        mineGameMetric = MineGameMetric(3, 3, 8)
        val cells =
            Cells(
                listOf(
                    Cell.EmptyCell(Coordinate(Row(2), Col(2))),
                    Cell.MineCell(Coordinate(Row(1), Col(1))),
                    Cell.MineCell(Coordinate(Row(1), Col(2))),
                    Cell.MineCell(Coordinate(Row(1), Col(3))),
                    Cell.MineCell(Coordinate(Row(2), Col(1))),
                    Cell.MineCell(Coordinate(Row(2), Col(3))),
                    Cell.MineCell(Coordinate(Row(3), Col(1))),
                    Cell.MineCell(Coordinate(Row(3), Col(2))),
                    Cell.MineCell(Coordinate(Row(3), Col(3))),
                ),
            )

        sut = MineBoard(mineGameMetric, cells)
    }

    describe("countAdjacentMines") {
        it("주변 지뢰 개수를 센다.") {
            val actual = sut.countAdjacentMines(Cell.EmptyCell(Coordinate(Row(2), Col(2))))
            actual shouldBe 8
        }
    }

    describe("isMineCell test") {
        context("지뢰셀인 경우") {
            it("should be true") {
                sut.isMineCell(Coordinate(3, 3)) shouldBe true
            }
        }

        context("비어있는 셀 인 경우") {
            it("should be false") {
                sut.isMineCell(Coordinate(2, 2)) shouldBe false
            }
        }
    }

    describe("isAnyMineCellOpened test") {
        context("하나 이상의 지뢰 셀을 오픈한 경우") {
            it("should be true") {
                mineGameMetric = MineGameMetric(3, 3, 8)
                val cells =
                    Cells(
                        listOf(
                            Cell.EmptyCell(Coordinate(1, 1)),
                            Cell.MineCell(Coordinate(2, 2), CellStatus.OPEN),
                        ),
                    )
                sut = MineBoard(mineGameMetric, cells)
                sut.isAnyMineCellOpened().shouldBeTrue()
            }
        }

        context("지뢰셀을 하나도 오픈하지 않은 경우") {
            it("should be false") {
                mineGameMetric = MineGameMetric(3, 3, 8)
                val cells =
                    Cells(
                        listOf(
                            Cell.MineCell(Coordinate(1, 1), CellStatus.CLOSED),
                            Cell.MineCell(Coordinate(2, 2), CellStatus.CLOSED),
                        ),
                    )
                sut = MineBoard(mineGameMetric, cells)
                sut.isAnyMineCellOpened().shouldBeFalse()
            }
        }
    }

    describe("isAllEmptyCellsOpened test") {
        mineGameMetric = MineGameMetric(3, 3, 5)

        beforeTest {
            val cellList =
                listOf(
                    Cell.MineCell(
                        Coordinate(1, 1),
                    ),
                    Cell.EmptyCell(
                        Coordinate(1, 2),
                        CellStatus.OPEN,
                    ),
                    Cell.EmptyCell(
                        Coordinate(1, 3),
                        CellStatus.OPEN,
                    ),
                    Cell.EmptyCell(
                        Coordinate(2, 1),
                        CellStatus.OPEN,
                    ),
                    Cell.MineCell(
                        Coordinate(2, 2),
                    ),
                    Cell.MineCell(
                        Coordinate(2, 3),
                    ),
                    Cell.MineCell(
                        Coordinate(3, 1),
                    ),
                    Cell.EmptyCell(
                        Coordinate(3, 2),
                        CellStatus.OPEN,
                    ),
                    Cell.EmptyCell(
                        Coordinate(3, 3),
                        CellStatus.OPEN,
                    ),
                )
            sut = MineBoard(mineGameMetric, Cells(cellList))
        }

        context("비어있는 셀을 모두 오픈한 경우") {
            it("should be true") {
                sut.isAllEmptyCellsOpened() shouldBe true
            }
        }
    }

    describe("좌표에 해당하는 셀을 open한다.") {
        mineGameMetric = MineGameMetric(3, 3, 5)

        beforeTest {
            val cellList =
                listOf(
                    Cell.MineCell(
                        Coordinate(1, 1),
                    ),
                    Cell.EmptyCell(
                        Coordinate(1, 2),
                    ),
                )
            sut = MineBoard(mineGameMetric, Cells(cellList))

            sut.openCell(Coordinate(1, 1))

            cellList[0].status shouldBe CellStatus.OPEN
        }
    }

    describe("getAdjacentCoordinates test") {
        it("인접한 셀의 좌표를 리턴한다.") {
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
            val sut = MineBoard(mineGameMetric, cells)
            val actual = sut.getAdjacentCoordinates(cellList[0])
            actual.size shouldBe 3
        }
    }
})
