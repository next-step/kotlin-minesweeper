package next.step.minesweeper.domain.board.state

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import next.step.minesweeper.domain.mine.MineCount

class CoveredStateTest : DescribeSpec({

    describe("CoveredState") {
        val state = CoveredState(MineFreeState)
        context("notifyMine") {
            it("1번 호출하면 주변 지뢰개수 1개인 NearMineState 제공") {
                val result = state.notifyMine()

                result shouldBe CoveredState(NearMineState.one())
            }
            it("2번 호출하면 주변 지뢰개수 2개인 NearMineState 제공") {
                val result = state.notifyMine().notifyMine()

                result shouldBe CoveredState(NearMineState(MineCount(2)))
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
