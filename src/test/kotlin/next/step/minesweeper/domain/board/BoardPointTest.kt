package next.step.minesweeper.domain.board

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import next.step.minesweeper.domain.board.state.CoveredState
import next.step.minesweeper.domain.board.state.MineFreeState
import next.step.minesweeper.domain.board.state.MineState
import next.step.minesweeper.domain.board.state.NearMineState
import next.step.minesweeper.domain.mine.MineCount

class BoardPointTest : DescribeSpec({

    describe("BoardPoint") {
        context("생성") {
            it("지뢰 없는 상태로 생성") {
                BoardPoint.mineFree().state() shouldBe MineFreeState
            }
        }

        context("지뢰를 심으면") {
            it("mine 상태로 바뀜") {
                val point = BoardPoint.mineFree()

                point.plantMine()

                point.state() shouldBe MineState
            }
        }

        context("주변에 지뢰가 있다고 알림받으면") {
            it("처음 알림 받은 거면 주변 지뢰 개수 1인 NearMineState로 바뀜") {
                val point = BoardPoint.mineFree()

                point.notifyMine()

                point.state() shouldBe NearMineState(MineCount(1))
            }
            it("이전에도 알림을 받았으면 NearMineState의 지뢰 개수를 증가") {
                val point = BoardPoint.mineFree()

                repeat(3) {
                    point.notifyMine()
                }

                point.state() shouldBe NearMineState(MineCount(3))
            }
            it("covered 상태에 대해서는 알림이 와도 반응하지 않음") {
                val point = BoardPoint.mineFree()

                point.cover()
                point.notifyMine()

                point.state() shouldBe CoveredState(MineFreeState)
            }
            it("지뢰가 심어진 상태에 대해서는 알림이 와도 반응하지 않음") {
                val point = BoardPoint.mineFree()

                point.plantMine()
                point.notifyMine()

                point.state() shouldBe MineState
            }
        }
    }
})
