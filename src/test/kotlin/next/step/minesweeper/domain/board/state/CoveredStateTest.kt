package next.step.minesweeper.domain.board.state

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class CoveredStateTest : DescribeSpec({

    describe("CoveredState") {
        context("notifyMine 호출하면") {
            it("자신을 제공") {
                val state = CoveredState

                val result = state.notifyMine()

                result shouldBe state
            }
        }
    }
})
