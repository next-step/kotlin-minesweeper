package next.step.minesweeper.domain.board.state

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class MineStateTest : DescribeSpec({

    describe("MineState") {
        context("desc 호출하면") {
            it("상태 표현 제공") {
                MineState.desc() shouldBe "*"
            }
        }
    }
})
