package domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeSameInstanceAs

class CellsTest : DescribeSpec({
    describe("isAnyMineCellOpened test") {
        context("하나 이상의 지뢰 셀이 오픈 되어있는 경우") {
            it("should be true") {
                val sut =
                    Cells(
                        listOf(
                            Cell.MineCell(Coordinate(1, 1)),
                            Cell.MineCell(Coordinate(2, 1), CellStatus.OPEN),
                            Cell.MineCell(Coordinate(3, 1)),
                        ),
                    )

                sut.isAnyMineCellOpened() shouldBe true
            }
        }

        context("모든 지뢰 셀이 닫혀있는 경우") {
            it("should be false") {
                val sut =
                    Cells(
                        listOf(
                            Cell.MineCell(Coordinate(1, 1)),
                            Cell.EmptyCell(Coordinate(2, 1), CellStatus.OPEN),
                            Cell.MineCell(Coordinate(3, 1)),
                        ),
                    )

                sut.isAnyMineCellOpened() shouldBe false
            }
        }
    }

    describe("isAllEmptyCellsOpened test") {
        context("모든 빈셀이 열려있는 경우") {
            it("should be true") {
                val sut =
                    Cells(
                        listOf(
                            Cell.EmptyCell(Coordinate(1, 1), CellStatus.OPEN),
                            Cell.EmptyCell(Coordinate(2, 1), CellStatus.OPEN),
                            Cell.EmptyCell(Coordinate(3, 1), CellStatus.OPEN),
                        ),
                    )

                sut.isAllEmptyCellsOpened() shouldBe true
            }
        }

        context("모든 빈셀이 열려있지 않은 경우") {
            it("should be true") {
                val sut =
                    Cells(
                        listOf(
                            Cell.EmptyCell(Coordinate(1, 1), CellStatus.OPEN),
                            Cell.EmptyCell(Coordinate(2, 1), CellStatus.OPEN),
                            Cell.EmptyCell(Coordinate(3, 1)),
                        ),
                    )

                sut.isAllEmptyCellsOpened() shouldBe false
            }
        }
    }

    describe("get test") {
        it("입력받은 좌표에 해당하는 셀을 조회한다.") {
            val cellList =
                listOf(
                    Cell.EmptyCell(Coordinate(1, 1), CellStatus.OPEN),
                    Cell.EmptyCell(Coordinate(2, 1), CellStatus.OPEN),
                    Cell.EmptyCell(Coordinate(3, 1)),
                )
            val sut = Cells(cellList)
            sut.get(Coordinate(1, 1)) shouldBeSameInstanceAs cellList[0]
        }
    }

    describe("getBoardHeight test") {
        it("cell의 row 중 가장 큰 값을 조회한다.") {
            val sut =
                Cells(
                    listOf(
                        Cell.EmptyCell(Coordinate(10, 1), CellStatus.OPEN),
                        Cell.EmptyCell(Coordinate(20, 1), CellStatus.OPEN),
                        Cell.EmptyCell(Coordinate(30, 1)),
                    ),
                )

            sut.height shouldBe Row(30)
        }
    }

    describe("getBoardWidth test") {
        it("cell의 row 중 가장 큰 값을 조회한다.") {
            val sut =
                Cells(
                    listOf(
                        Cell.EmptyCell(Coordinate(10, 100), CellStatus.OPEN),
                        Cell.EmptyCell(Coordinate(20, 200), CellStatus.OPEN),
                        Cell.EmptyCell(Coordinate(30, 300)),
                    ),
                )

            sut.width shouldBe Col(300)
        }
    }
})
