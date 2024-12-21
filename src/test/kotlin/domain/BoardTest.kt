package domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class BoardTest : DescribeSpec({
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
})
