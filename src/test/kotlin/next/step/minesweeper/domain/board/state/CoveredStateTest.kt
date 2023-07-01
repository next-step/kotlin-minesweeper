package next.step.minesweeper.domain.board.state

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class CoveredStateTest : DescribeSpec({

    describe("CoveredState") {
        context("desc 호출하면") {
            it("상태 표현 제공") {
                CoveredState.desc() shouldBe "C"
            }
        }
    }
})
