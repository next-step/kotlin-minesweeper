package next.step.minesweeper.domain.board.state

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class NearMineStateTest : DescribeSpec({

    describe("NearMineState") {
        context("increase 호출하면") {
            it("주변 지뢰 개수 증가") {
                val state = NearMineState.one()

                state.increase()

                state.count() shouldBe 2
            }
        }
    }

})
