package minesweeper.domain

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class BoardFieldTest : DescribeSpec({

    describe("isMine") {
        it("지뢰 유무를 확인할 수 있다") {
            val coordinate = Coordinate(CoordinateIndex(0), CoordinateIndex(0))

            assertSoftly {
                BoardField(coordinate, true).isMine shouldBe true
                BoardField(coordinate, false).isMine shouldBe false
            }
        }
    }
})

data class BoardField(val coordinate: Coordinate, val isMine: Boolean)
