package next.step.minesweeper.domain.board

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
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
        context("can uncover") {
            it("지뢰가 안에 없고 덮여있으면 true") {
                val point = BoardPoint.mineFree()

                point.cover()

                point.canUncover() shouldBe true
            }
            it("지뢰가 주변에 있지만 안에 없고 덮여있으면 true") {
                val point = BoardPoint.mineFree()

                point.notifyMine()
                point.cover()

                point.canUncover() shouldBe true
            }
            it("지뢰가 있고 덮여있으면 false") {
                val point = BoardPoint.mineFree()

                point.plantMine()
                point.cover()

                point.canUncover() shouldBe false
            }
        }
        context("덮여있지 않으면 false") {
            withData(
                listOf(MineFreeState, MineState, NearMineState.one()),
            ) { state ->
                BoardPoint(state).canUncover() shouldBe false
            }
        }
        context("is mine") {
            it("덮여있지 않고, 지뢰가 있으면 true") {
                BoardPoint(MineState).isUncoveredMine() shouldBe true
            }
        }
        context("is mine free") {
            it("덮여있지 않고, 지뢰가 없고, 주위에도 없으면 true") {
                BoardPoint.mineFree().isUncoveredMineFree() shouldBe true
            }
        }
    }
})
