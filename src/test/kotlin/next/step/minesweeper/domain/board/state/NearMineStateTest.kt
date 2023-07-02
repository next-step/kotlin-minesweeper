package next.step.minesweeper.domain.board.state

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class NearMineStateTest : DescribeSpec({

    describe("NearMineState") {
        context("notifyMine 호출하면") {
            it("주변 지뢰 개수 증가된 NearMineState 제공") {
                val state = NearMineState.one()

                val result = state.notifyMine()

                result.count() shouldBe 2
            }
        }
    }
})
