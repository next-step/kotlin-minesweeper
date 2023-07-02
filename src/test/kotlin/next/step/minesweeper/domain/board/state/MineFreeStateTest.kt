package next.step.minesweeper.domain.board.state

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class MineFreeStateTest : DescribeSpec({

    describe("MineFreeState") {
        context("notifyMine 호출하면") {
            it("주변 지뢰 개수 1개인 NearMineState 제공") {
                val state = MineFreeState

                val result = state.notifyMine()

                result shouldBe NearMineState.one()
            }
        }
    }
})
