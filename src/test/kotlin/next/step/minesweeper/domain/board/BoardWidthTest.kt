package next.step.minesweeper.domain.board

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.assertThrows

class BoardWidthTest : DescribeSpec({

    describe("BoardWidth") {
        context("0이하의 값으로 생성하면 예외 발생") {
            withData(
                listOf(-1, 0)
            ) { width ->
                assertThrows<IllegalArgumentException> { BoardWidth(width) }
            }
        }
        context("Position의 x가 범위에 없으면, false") {
            withData(
                listOf(10, -1)
            ) { x ->
                BoardWidth(10).inRange(x) shouldBe false
            }
        }
        context("Position의 x가 범위에 있으면, true") {
            withData(
                listOf(9, 0)
            ) { x ->
                BoardWidth(10).inRange(x) shouldBe true
            }
        }
    }
})
