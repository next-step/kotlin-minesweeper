package minesweeper

import io.kotest.core.spec.style.DescribeSpec
import minesweeper.domain.BoardSize
import org.junit.jupiter.api.assertThrows

class BoardSizeTest : DescribeSpec({
    describe("constructor") {
        context("높이나 너비 값이 1보다 작으면") {
            it("IllegalArgumentException 예외가 발생한다.") {
                assertThrows<IllegalArgumentException> {
                    BoardSize(0, 0)
                }
            }
        }
    }
})
