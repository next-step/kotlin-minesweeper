package next.step.minesweeper.domain.board

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
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
        context("Position의 x가 범위에 없으면, 예외발생") {
            withData(
                listOf(10, -1)
            ) { x ->
                assertThrows<IllegalArgumentException> {
                    BoardWidth(10).requireInRange(x)
                }.shouldHaveMessage("x 위치는 0보다 크고, 10 보다 작아야 합니다.")
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
        context("method") {
            val width = BoardWidth(10)
            it("width 제공") {
                width.width() shouldBe 10
            }
            it("rangeMap으로 변환 제공") {
                width.rangeMap { it + 3 } shouldBe listOf(3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
            }
        }
    }
})
