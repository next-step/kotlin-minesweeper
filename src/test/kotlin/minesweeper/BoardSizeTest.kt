package minesweeper

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import minesweeper.domain.BoardSize

class BoardSizeTest : DescribeSpec({
    describe("constructor") {
        context("높이나 너비 값이 1보다 작으면") {
            it("IllegalArgumentException 예외가 발생한다.") {
                shouldThrow<IllegalArgumentException> {
                    BoardSize(0, 0)
                }
            }
        }
    }
})
