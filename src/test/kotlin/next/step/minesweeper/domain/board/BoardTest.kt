package next.step.minesweeper.domain.board

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class BoardTest : DescribeSpec({

    describe("Board") {
        val board = Board.of(BoardHeight(10), BoardWidth.of(10))
        context("method") {
            it("너비 제공") {
                board.width() shouldBe 10
            }
            it("높이 제공") {
                board.height() shouldBe 10
            }
            it("넓이 제공") {
                board.area() shouldBe 100
            }
        }
    }
})
