package next.step.minesweeper.domain.board

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import next.step.minesweeper.domain.board.state.CoveredState
import next.step.minesweeper.domain.board.state.MineFreeState
import next.step.minesweeper.domain.board.state.MineState
import org.junit.jupiter.api.assertThrows

class BoardRowTest : DescribeSpec({

    describe("BoardRow") {
        context("지뢰 심으면") {
            it("특정 위치만 MineState로 바뀜") {
                val boardRow =
                    BoardRow(listOf(BoardPoint(MineFreeState), BoardPoint(MineFreeState), BoardPoint(MineFreeState)))

                boardRow.plantMine(1)

                boardRow.points() shouldBe listOf(
                    BoardPoint(MineFreeState),
                    BoardPoint(MineState),
                    BoardPoint(MineFreeState)
                )
            }
        }

        context("지뢰 최대 너비 넘어가게 심으면") {
            it("예외 발생") {
                val boardRow =
                    BoardRow(listOf(BoardPoint(MineFreeState), BoardPoint(MineFreeState), BoardPoint(MineFreeState)))

                assertThrows<IllegalArgumentException> {
                    boardRow.plantMine(3)
                }.shouldHaveMessage("지뢰 x 위치는 3 보다 작아야 합니다.")
            }
        }

        context("cover") {
            it("Point가 모두 CoveredState가 됨") {
                val boardRow =
                    BoardRow(listOf(BoardPoint(MineFreeState), BoardPoint(MineFreeState), BoardPoint(MineFreeState)))

                boardRow.cover()

                boardRow.points() shouldBe listOf(
                    BoardPoint(CoveredState(MineFreeState)),
                    BoardPoint(CoveredState(MineFreeState)),
                    BoardPoint(CoveredState(MineFreeState))
                )
            }
        }
    }
})
