package minesweeper

import io.kotest.core.spec.style.DescribeSpec
import minesweeper.domain.MineCount
import org.junit.jupiter.api.assertThrows

class MineCountTest : DescribeSpec({
    describe("constructor") {
        context("값이 1보다 작으면") {
            it("IllegalArgumentException 예외가 발생한다.") {
                assertThrows<IllegalArgumentException> {
                    MineCount(0)
                }
            }
        }
    }
})
