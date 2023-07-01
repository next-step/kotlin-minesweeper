package next.step.minesweeper.domain.board

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import next.step.minesweeper.domain.board.state.CoveredState
import next.step.minesweeper.domain.board.state.MineState

class BoardPointTest : DescribeSpec({

    describe("BoardPoint") {
        context("생성") {
            it("covered 상태로 생성") {
                val point = BoardPoint.covered()

                assertSoftly {
                    point.state() shouldBe CoveredState
                }
            }
        }

        context("지뢰를 심으면") {
            it("mine 상태로 바뀜") {
                val point = BoardPoint.covered()

                point.plantMine()

                assertSoftly {
                    point.state() shouldBe MineState
                }
            }
        }
    }
})
