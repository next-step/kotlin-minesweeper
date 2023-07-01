package next.step.minesweeper.domain.board

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
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
    }
})
