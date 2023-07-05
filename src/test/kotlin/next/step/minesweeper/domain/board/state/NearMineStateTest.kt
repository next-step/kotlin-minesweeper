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
        context("uncover 호출하면") {
            it("자신을 제공") {
                val result = state.uncover()

                result shouldBe NearMineState.one()
            }
        }
        context("isMine 호출하면") {
            it("항상 false") {
                state.isMine() shouldBe false
            }
        }
    }
})
