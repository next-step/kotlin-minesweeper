package domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class CellTest : DescribeSpec({
    describe("isMineCell Test") {
        context("Cell이 MineCell 인 경우") {
            it("should be true") {
                val cell = Cell.MineCell(Coordinate(Row(3), Col(3)))
                cell.isMineCell() shouldBe true
            }
        }
    }
})
