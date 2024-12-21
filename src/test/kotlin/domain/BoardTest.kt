package domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class BoardTest : DescribeSpec({
    describe("countAdjacentMines") {
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

        it("주변 지뢰 개수를 센다.") {
            val actual = sut.countAdjacentMines(Cell.EmptyCell(Coordinate(Row(2), Col(2))))
            actual shouldBe 8
        }
    }
})
