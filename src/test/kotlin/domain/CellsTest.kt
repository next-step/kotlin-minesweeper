package domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class CellsTest : DescribeSpec({
    describe("generate test") {
        it("지뢰를 생성하고 남은 칸의 수는 비어있는 칸이다.") {
            val sut = Cells.generateWithMines(3, 3, setOf(Coordinate(BoardHeight(2), BoardWidth(2))))
            sut.emptyCells().size shouldBe 8
        }

        it("지뢰 위치만큼 지뢰 칸을 차지한다.") {
            val sut = Cells.generateWithMines(3, 3, setOf(Coordinate(BoardHeight(2), BoardWidth(2))))
            sut.mineCells().size shouldBe 1
        }
    }
})
