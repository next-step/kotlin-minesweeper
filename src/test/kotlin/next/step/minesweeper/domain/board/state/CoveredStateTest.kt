package next.step.minesweeper.domain.board.state

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class CoveredStateTest : DescribeSpec({

    describe("CoveredState") {
        val state = CoveredState(MineFreeState)
        context("notifyMine 호출하면") {
            it("자신을 제공") {
                val result = state.notifyMine()

                result shouldBe state
            }
        }
        context("cover 호출하면") {
            it("자신을 제공") {
                val result = state.cover()

                result shouldBe state
            }
        }
        context("uncover 호출하면") {
            it("감춘 상태를 제공") {
                val result = state.uncover()

                result shouldBe MineFreeState
            }
        }
    }
})
