package domain

import domain.strategy.RandomMineCellGenerator
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class CellsTest : DescribeSpec({
    describe("generate test") {
        lateinit var mineGameMetric: MineGameMetric
        beforeTest { mineGameMetric = MineGameMetric(3, 3, 1) }

        it("지뢰를 생성하고 남은 칸의 수는 비어있는 칸이다.") {
            val sut = Cells.generateWithMines(mineGameMetric, RandomMineCellGenerator())
            sut.emptyCells().size shouldBe 8
        }

        it("지뢰 위치만큼 지뢰 칸을 차지한다.") {
            val sut = Cells.generateWithMines(mineGameMetric, RandomMineCellGenerator())
            sut.mineCells().size shouldBe 1
        }
    }

    describe("getCoordinateIs test") {
        lateinit var cellList: List<Cell>

        beforeTest {
            cellList =
                listOf(
                    Cell.EmptyCell(
                        Coordinate(Row(1), Col(1)),
                    ),
                    Cell.EmptyCell(
                        Coordinate(Row(1), Col(2)),
                    ),
                    Cell.EmptyCell(
                        Coordinate(Row(2), Col(1)),
                    ),
                    Cell.EmptyCell(
                        Coordinate(Row(2), Col(2)),
                    ),
                )
        }

        context("조회하려는 좌표가 유효한 좌표인 경우") {
            val sut = Cells(cellList)
            it("해당 셀을 리턴한다.") {
                sut.get(Coordinate(Row(1), Col(1))) shouldBe cellList[0]
            }
        }

        context("조회하려는 좌표가 유효하지 않은 좌표인 경우") {
            it("throw an exception.") {
                val sut = Cells(cellList)
                shouldThrow<NoSuchElementException> {
                    sut.get(Coordinate(Row(3), Col(3)))
                }
            }
        }
    }

    describe("countOpenedMineCells test") {
        lateinit var cellList: List<Cell>

        beforeTest {
            cellList =
                listOf(
                    Cell.MineCell(
                        Coordinate(1, 1), CellStatus.OPEN,
                    ),
                    Cell.EmptyCell(
                        Coordinate(1, 2),
                    ),
                    Cell.MineCell(
                        Coordinate(2, 1),
                    ),
                    Cell.MineCell(
                        Coordinate(2, 2),
                    ),
                )
            val sut = Cells(cellList)
            it("should be 1") {
                sut.countOpenedMineCells() shouldBe 1
            }
        }
    }

    describe("countEmptyMineCells test") {
        lateinit var cellList: List<Cell>

        beforeTest {
            cellList =
                listOf(
                    Cell.MineCell(
                        Coordinate(1, 1), CellStatus.OPEN,
                    ),
                    Cell.EmptyCell(
                        Coordinate(1, 2), CellStatus.OPEN,
                    ),
                    Cell.EmptyCell(
                        Coordinate(1, 3),
                    ),
                    Cell.EmptyCell(
                        Coordinate(2, 1),
                    ),
                    Cell.MineCell(
                        Coordinate(2, 2),
                    ),
                    Cell.MineCell(
                        Coordinate(2, 3),
                    ),
                    Cell.MineCell(
                        Coordinate(3, 1), CellStatus.OPEN,
                    ),
                    Cell.EmptyCell(
                        Coordinate(3, 2), CellStatus.OPEN,
                    ),
                    Cell.EmptyCell(
                        Coordinate(3, 3),
                    ),
                )
        }

        it("should be 2") {
            val sut = Cells(cellList)
            sut.countOpenedEmptyCells() shouldBe 2
        }
    }

    describe("countEmptyCells test") {
        lateinit var cellList: List<Cell>
        beforeTest {
            cellList =
                listOf(
                    Cell.MineCell(
                        Coordinate(1, 1),
                    ),
                    Cell.EmptyCell(
                        Coordinate(1, 2), CellStatus.OPEN,
                    ),
                    Cell.EmptyCell(
                        Coordinate(1, 3), CellStatus.OPEN,
                    ),
                    Cell.EmptyCell(
                        Coordinate(2, 1), CellStatus.OPEN,
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
                        Coordinate(3, 2), CellStatus.OPEN,
                    ),
                    Cell.EmptyCell(
                        Coordinate(3, 3), CellStatus.OPEN,
                    ),
                )
        }

        it("should be 5") {
            val sut = Cells(cellList)
            sut.countEmptyCells() shouldBe 5
        }
    }
})
