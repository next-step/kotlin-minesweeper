package next.step.minesweeper.domain.board.state

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class MineFreeStateTest : DescribeSpec({

    describe("MineFreeState") {
        val state = MineFreeState

        context("notifyMine 호출하면") {
            it("주변 지뢰 개수 1개인 NearMineState 제공") {
                val result = state.notifyMine()

                result shouldBe NearMineState.one()
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

                result shouldBe MineFreeState
            }
        }
        context("hasNoMine 호출하면") {
            it("항상 true") {
                state.hasNoMine() shouldBe true
            }
        }
    }
})
