package next.step.minesweeper.domain.board

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import next.step.minesweeper.domain.board.state.CoveredState
import next.step.minesweeper.domain.board.state.MineState
import next.step.minesweeper.domain.mine.MinePosition

class BoardRowTest : DescribeSpec({

    describe("BoardRow") {
        context("생성") {
            it("covered 상태로 원하는 board width만큼 생성") {
                val boardRow = BoardRow.covered(10)

                assertSoftly {
                    boardRow.size() shouldBe 10
                    boardRow.descs() shouldBe (1..10).map { "C" }
                }
            }
        }
        context("지뢰 심으면") {
            it("특정 위치만 MineState로 바뀜") {
                val boardRow = BoardRow.covered(3)

                val result = boardRow.plantMine(MinePosition(1, 0))

                result shouldBe listOf(
                    BoardPoint(CoveredState),
                    BoardPoint(MineState),
                    BoardPoint(CoveredState)
                )
            }
        }

    }
})
