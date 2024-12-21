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
})
