package next.step.minesweeper.domain.board.state

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class MineStateTest : DescribeSpec({

    describe("MineState") {
        val state = MineState

        context("notifyMine 호출하면") {
            it("자신을 제공") {

                val result = state.notifyMine()

                result shouldBe state
            }
        }
        context("cover 호출하면") {
            it("CoveredState 제공") {
                val result = state.cover()

                result shouldBe CoveredState(state)
            }
        }
        context("uncover 호출하면") {
            it("자신을 제공") {
                val result = state.uncover()

                result shouldBe MineState
            }
        }
    }
})
