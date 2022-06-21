package minesweeper.domain

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class BoardFieldTest : DescribeSpec({

    describe("isMine") {
        it("지뢰 유무를 확인할 수 있다") {
            val coordinate = Coordinate(0, 0)

            assertSoftly {
                BoardField.mine(coordinate).isMine shouldBe true
                BoardField.nonMine(coordinate).isMine shouldBe false
            }
        }
    }
})
