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
        context("uncover 호출하면") {
            it("자신을 제공") {
                val result = state.uncover()

                result shouldBe MineFreeState
            }
        }
        context("isMine 호출하면") {
            it("항상 false") {
                state.isMine() shouldBe false
            }
        }
    }
})
