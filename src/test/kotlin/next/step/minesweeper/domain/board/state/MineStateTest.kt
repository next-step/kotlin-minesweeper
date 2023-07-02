package next.step.minesweeper.domain.board.state

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class MineStateTest : DescribeSpec({

    describe("MineState") {
        context("notifyMine 호출하면") {
            it("자신을 제공") {
                val state = MineState

                val result = state.notifyMine()

                result shouldBe state
            }
        }
    }
})
