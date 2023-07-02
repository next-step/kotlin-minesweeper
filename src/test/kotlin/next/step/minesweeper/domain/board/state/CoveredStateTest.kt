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
        context("hasNoMine 호출하면") {
            it("감춘 상태가 지뢰가 아니면 true") {
                state.hasNoMine() shouldBe true
            }
            it("감춘 상태가 지뢰이면 false") {
                val mineCovered = CoveredState(MineState)

                mineCovered.hasNoMine() shouldBe false
            }
        }
    }
})
