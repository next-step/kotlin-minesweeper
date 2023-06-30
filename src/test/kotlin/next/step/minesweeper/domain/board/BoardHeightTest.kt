package next.step.minesweeper.domain.board

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import org.junit.jupiter.api.assertThrows

class BoardHeightTest : DescribeSpec({

    describe("BoardHeight") {
        context("0이하의 값으로 생성하면 예외 발생") {
            withData(
                listOf(-1, 0)
            ) { height ->
                assertThrows<IllegalArgumentException> { BoardHeight.of(height) }
            }
        }
    }
})
