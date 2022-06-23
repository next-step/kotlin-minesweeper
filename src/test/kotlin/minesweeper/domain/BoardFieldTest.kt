package minesweeper.domain

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class BoardFieldTest : DescribeSpec({

    describe("open") {
        it("지뢰 판 필드를 열 수 있다.") {
            val numberField = BoardField.nonMine(Coordinate(0, 1))
            val mineField = BoardField.mine(Coordinate(1, 1))

            numberField.open()
            mineField.open()

            assertSoftly {
                numberField.isOpen shouldBe true
                mineField.isOpen shouldBe true
            }
        }

        it("지뢰 판 필드 처음 열림 여부는 false 이다") {
            val numberField = BoardField.nonMine(Coordinate(0, 1))
            val mineField = BoardField.mine(Coordinate(1, 1))

            assertSoftly {
                numberField.isOpen shouldBe false
                mineField.isOpen shouldBe false
            }
        }
    }
})
