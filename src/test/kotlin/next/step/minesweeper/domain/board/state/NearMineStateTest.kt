package next.step.minesweeper.domain.board.state

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class NearMineStateTest : DescribeSpec({

    describe("NearMineState") {
        val state = NearMineState.one()
        
        context("notifyMine 호출하면") {
            it("주변 지뢰 개수 증가된 NearMineState 제공") {

                val result = state.notifyMine()

                result.count() shouldBe 2
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

                result shouldBe NearMineState.one()
            }
        }
    }
})
