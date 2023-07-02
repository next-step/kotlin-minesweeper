package next.step.minesweeper.domain.board

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import next.step.minesweeper.domain.board.state.MineFreeState
import next.step.minesweeper.domain.board.state.MineState
import org.junit.jupiter.api.assertThrows

class BoardRowTest : DescribeSpec({

    describe("BoardRow") {
        val boardRow =
            BoardRow(listOf(BoardPoint(MineFreeState), BoardPoint(MineFreeState), BoardPoint(MineFreeState)))

        context("지뢰 심으면") {
            it("특정 위치만 MineState로 바뀜") {
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
                assertThrows<IllegalArgumentException> {
                    boardRow.plantMine(3)
                }.shouldHaveMessage("지뢰 x 위치는 3 보다 작아야 합니다.")
            }
        }
    }
})
