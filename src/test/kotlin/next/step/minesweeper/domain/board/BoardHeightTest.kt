package next.step.minesweeper.domain.board

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import next.step.minesweeper.domain.position.Position
import org.junit.jupiter.api.assertThrows

class BoardHeightTest : DescribeSpec({

    describe("BoardHeight") {
        context("0이하의 값으로 생성하면 예외 발생") {
            withData(
                listOf(-1, 0)
            ) { height ->
                assertThrows<IllegalArgumentException> { BoardHeight(height) }
            }
        }
        context("Position의 y가 범위에 없으면, false") {
            withData(
                listOf(Position(0, 10), Position(0, -1))
            ) { position ->
                BoardHeight(10).inRange(position) shouldBe false
            }
        }
        context("Position의 y가 범위에 있으면, true") {
            withData(
                listOf(Position(0, 9), Position(0, 0))
            ) { position ->
                BoardHeight(10).inRange(position) shouldBe true
            }
        }
    }
})
