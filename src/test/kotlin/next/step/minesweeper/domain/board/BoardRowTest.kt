package next.step.minesweeper.domain.board

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import next.step.minesweeper.domain.board.state.CoveredState
import next.step.minesweeper.domain.board.state.MineState
import next.step.minesweeper.domain.mine.MinePosition
import org.junit.jupiter.api.assertThrows

class BoardRowTest : DescribeSpec({

    describe("BoardRow") {
        context("생성") {
            it("covered 상태로 원하는 board width만큼 생성") {
                val boardRow = BoardRow.covered(BoardWidth(10))

                boardRow.size() shouldBe 10
            }
            it("mine free 상태로 원하는 board width만큼 생성") {
                val boardRow = BoardRow.mineFree(BoardWidth(10))

                boardRow.size() shouldBe 10
            }
        }
        context("지뢰 심으면") {
            it("특정 위치만 MineState로 바뀜") {
                val boardRow = BoardRow.covered(BoardWidth(3))

                boardRow.plantMine(MinePosition(1, 0))

                boardRow.points shouldBe listOf(
                    BoardPoint(CoveredState),
                    BoardPoint(MineState),
                    BoardPoint(CoveredState)
                )
            }
        }

        context("지뢰 최대 너비 넘어가게 심으면") {
            it("예외 발생") {
                assertThrows<IllegalArgumentException> {
                    BoardRow.covered(BoardWidth(3)).plantMine(MinePosition(3, 0))
                }.shouldHaveMessage("지뢰 x 위치는 3 보다 작아야 합니다.")
            }
        }
    }
})
