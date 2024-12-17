package domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import strategy.FixedMineCellGenerator

class CellsTest : DescribeSpec({
    describe("generate test") {
        it("비어 있는 셀을 생성한다.") {
            val sut = Cells.generate(heightRange = 1..3, widthRange = 1..3)
            sut.emptyCells().size shouldBe 9
        }
    }

    describe("placeMines test") {
        lateinit var sut: Cells
        lateinit var coordinate: Coordinate
        beforeTest {
            coordinate = Coordinate(BoardHeight(3), BoardWidth(3))
            sut = Cells.generate(heightRange = 1..coordinate.height.value, widthRange = 1..coordinate.width.value)
        }
        it("지뢰 셀을 배치한다.") {
            val mineCells = FixedMineCellGenerator().execute(coordinate, MineCount(2)).toList()
            val actual = sut.placeMines(Cells(mineCells))
            actual.mineCells().size shouldBe 2
            actual.emptyCells().size shouldBe 7
        }
    }
})
