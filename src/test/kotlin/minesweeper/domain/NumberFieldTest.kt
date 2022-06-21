package minesweeper.domain

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class NumberFieldTest : DescribeSpec({

    describe("number") {
        it("필드들이 주어지면 자신을 제외한 주변 8개 사각형에 포함된 지뢰 개수를 확인할 수 있다") {
            /**
             *  * C *
             *  C C C
             *  * C C
             */
            val boardFields = BoardFields(
                listOf(
                    BoardField.mine(Coordinate(0, 0)),
                    BoardField.nonMine(Coordinate(0, 1)),
                    BoardField.mine(Coordinate(0, 2)),
                    BoardField.nonMine(Coordinate(1, 0)),
                    BoardField.nonMine(Coordinate(1, 1)),
                    BoardField.nonMine(Coordinate(1, 2)),
                    BoardField.mine(Coordinate(2, 0)),
                    BoardField.nonMine(Coordinate(2, 1)),
                    BoardField.nonMine(Coordinate(2, 2)),
                )
            )

            assertSoftly {
                NumberField(Coordinate(0, 1)).number(boardFields) shouldBe 2
                NumberField(Coordinate(1, 1)).number(boardFields) shouldBe 3
                NumberField(Coordinate(2, 2)).number(boardFields) shouldBe 0
            }
        }
    }
})
