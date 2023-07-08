package next.step.minesweeper.domain.board

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import next.step.minesweeper.domain.board.state.CoveredState
import next.step.minesweeper.domain.board.state.MineFreeState
import next.step.minesweeper.domain.board.state.MineState
import next.step.minesweeper.domain.board.state.NearMineState
import next.step.minesweeper.domain.position.Position
import next.step.minesweeper.domain.position.Positions

class BoardPointsTest : DescribeSpec({

    describe("BoardPoints") {
        context("생성 시점에 주어진 지뢰 위치에 지뢰가 생김") {
            it("해당 위치의 State가 MineState가 됨") {
                val points = BoardPoints.of(BoardArea.of(3, 3), Positions(setOf(Position(1, 1))))

                points.groupByRow() shouldBe
                    listOf(
                        listOf(
                            BoardPoint(CoveredState(NearMineState.one())),
                            BoardPoint(CoveredState(NearMineState.one())),
                            BoardPoint(CoveredState(NearMineState.one())),
                        ),
                        listOf(
                            BoardPoint(CoveredState(NearMineState.one())),
                            BoardPoint(CoveredState(MineState)),
                            BoardPoint(CoveredState(NearMineState.one())),
                        ),
                        listOf(
                            BoardPoint(CoveredState(NearMineState.one())),
                            BoardPoint(CoveredState(NearMineState.one())),
                            BoardPoint(CoveredState(NearMineState.one())),
                        ),
                    )
            }
        }

        context("can uncover") {
            it("더 열 수 있는 위치가 있으면 true") {
                val points = BoardPoints(
                    mapOf(Position(0, 0) to BoardPoint.mineFree()),
                )

                points.canUncover() shouldBe true
            }

            it("더 열 수 있는 위치가 없으면 false") {
                val points = BoardPoints(
                    mapOf(
                        Position(0, 0) to BoardPoint(MineFreeState),
                        Position(1, 0) to BoardPoint(MineFreeState),
                        Position(2, 0) to BoardPoint(MineFreeState),
                        Position(0, 1) to BoardPoint(MineFreeState),
                        Position(1, 1) to BoardPoint(CoveredState(MineState)),
                        Position(2, 1) to BoardPoint(MineFreeState),
                        Position(0, 2) to BoardPoint(MineFreeState),
                        Position(1, 2) to BoardPoint(MineFreeState),
                        Position(2, 2) to BoardPoint(MineFreeState),
                    ),
                )

                points.canUncover() shouldBe false
            }
        }

        context("특정 위치를 열면-tailRec") {
            it("해당 위치 주변의 가능한 곳까지 열림") {
                val points = BoardPoints(
                    mapOf(
                        Position(0, 0) to BoardPoint(CoveredState(MineFreeState)),
                        Position(1, 0) to BoardPoint(CoveredState(MineFreeState)),
                        Position(2, 0) to BoardPoint(CoveredState(MineFreeState)),
                        Position(0, 1) to BoardPoint(CoveredState(NearMineState.one())),
                        Position(1, 1) to BoardPoint(CoveredState(MineState)),
                        Position(2, 1) to BoardPoint(CoveredState(NearMineState.one())),
                        Position(0, 2) to BoardPoint(CoveredState(NearMineState.one())),
                        Position(1, 2) to BoardPoint(CoveredState(NearMineState.one())),
                        Position(2, 2) to BoardPoint(CoveredState(NearMineState.one())),
                    ),
                )

                points.uncover(Position(1, 0), BoardArea.of(3, 3))

                points.groupByRow() shouldBe
                    listOf(
                        listOf(
                            BoardPoint(MineFreeState),
                            BoardPoint(MineFreeState),
                            BoardPoint(MineFreeState),
                        ),
                        listOf(
                            BoardPoint(NearMineState.one()),
                            BoardPoint(CoveredState(MineState)),
                            BoardPoint(NearMineState.one()),
                        ),
                        listOf(
                            BoardPoint(CoveredState(NearMineState.one())),
                            BoardPoint(CoveredState(NearMineState.one())),
                            BoardPoint(CoveredState(NearMineState.one())),
                        ),
                    )
            }
        }
    }
})
