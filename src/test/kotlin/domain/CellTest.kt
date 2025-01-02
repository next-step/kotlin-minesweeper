package domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class CellTest : DescribeSpec({
    describe("isMineCell test") {
        context("지뢰 셀인 경우") {
            it("should be true") {
                Cell.MineCell(Coordinate(1, 1)).isMineCell().shouldBeTrue()
            }
        }

        context("빈 셀인 경우") {
            it("should be false") {
                Cell.EmptyCell(Coordinate(1, 1)).isMineCell().shouldBeFalse()
            }
        }
    }

    describe("셀은 open 또는 closed 상태를 갖는다.") {
        it("처음 초기화 된 셀의 상태는 Closed 이다") {
            Cell.MineCell(Coordinate(1, 1)).status shouldBe CellStatus.CLOSED
            Cell.EmptyCell(Coordinate(1, 1)).status shouldBe CellStatus.CLOSED
        }

        context("open 함수를 호출한 경우") {
            it("셀의 상태가 OPEN으로 변경된다.") {
                val sut = Cell.MineCell(Coordinate(1, 1))

                sut.status shouldBe CellStatus.CLOSED
                sut.open()
                sut.status shouldBe CellStatus.OPEN
            }
        }
    }
})
