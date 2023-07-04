package next.step.minesweeper.domain.board

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import next.step.minesweeper.domain.board.state.CoveredState
import next.step.minesweeper.domain.board.state.MineFreeState
import next.step.minesweeper.domain.board.state.MineState
import next.step.minesweeper.domain.board.state.NearMineState

class BoardRowsTest : DescribeSpec({

    describe("BoardRows") {
        context("특정 위치에 지뢰 심으면") {
            it("해당 위치의 State가 MineState가 됨") {
                val rows = BoardRows(
                    listOf(
                        BoardRow(listOf(BoardPoint.mineFree(), BoardPoint.mineFree(), BoardPoint.mineFree())),
                        BoardRow(listOf(BoardPoint.mineFree(), BoardPoint.mineFree(), BoardPoint.mineFree())),
                    ),
                )

                rows.plantMine(BoardPosition.of(1, 1, BoardArea.of(2, 3)))

                rows shouldBe BoardRows(
                    listOf(
                        BoardRow(
                            listOf(
                                BoardPoint.mineFree(),
                                BoardPoint.mineFree(),
                                BoardPoint.mineFree(),
                            ),
                        ),
                        BoardRow(
                            listOf(
                                BoardPoint.mineFree(),
                                BoardPoint(CoveredState(MineState)),
                                BoardPoint.mineFree(),
                            ),
                        ),
                    ),
                )
            }
        }
        context("특정 위치에 지뢰가 있다고 알리면") {
            it("해당 위치의 주변이 NearMineState가 됨") {
                val rows = BoardRows(
                    listOf(
                        BoardRow(
                            listOf(
                                BoardPoint.mineFree(),
                                BoardPoint.mineFree(),
                                BoardPoint.mineFree(),
                            ),
                        ),
                        BoardRow(
                            listOf(
                                BoardPoint.mineFree(),
                                BoardPoint(CoveredState(MineState)),
                                BoardPoint.mineFree(),
                            ),
                        ),
                        BoardRow(listOf(BoardPoint.mineFree(), BoardPoint.mineFree(), BoardPoint.mineFree())),
                    ),
                )

                rows.notifyMine(BoardArea.of(3, 3).near(BoardPosition.of(1, 1, BoardArea.of(3, 3))))

                rows shouldBe BoardRows(
                    listOf(
                        BoardRow(
                            listOf(
                                BoardPoint(CoveredState(NearMineState.one())),
                                BoardPoint(CoveredState(NearMineState.one())),
                                BoardPoint(CoveredState(NearMineState.one())),
                            ),
                        ),
                        BoardRow(
                            listOf(
                                BoardPoint(CoveredState(NearMineState.one())),
                                BoardPoint(CoveredState(MineState)),
                                BoardPoint(CoveredState(NearMineState.one())),
                            ),
                        ),
                        BoardRow(
                            listOf(
                                BoardPoint(CoveredState(NearMineState.one())),
                                BoardPoint(CoveredState(NearMineState.one())),
                                BoardPoint(CoveredState(NearMineState.one())),
                            ),
                        ),
                    ),
                )
            }
        }

        context("can uncover") {
            it("더 열 수 있는 위치가 있으면 true") {
                val rows = BoardRows(
                    listOf(
                        BoardRow(listOf(BoardPoint.mineFree(), BoardPoint.mineFree(), BoardPoint.mineFree())),
                        BoardRow(
                            listOf(
                                BoardPoint(CoveredState(NearMineState.one())),
                                BoardPoint(CoveredState(MineState)),
                                BoardPoint.mineFree(),
                            ),
                        ),
                        BoardRow(listOf(BoardPoint.mineFree(), BoardPoint.mineFree(), BoardPoint.mineFree())),
                    ),
                )

                rows.canUncover() shouldBe true
            }

            it("더 열 수 있는 위치가 없으면 false") {
                val rows = BoardRows(
                    listOf(
                        BoardRow(
                            listOf(
                                BoardPoint(MineFreeState),
                                BoardPoint(MineFreeState),
                                BoardPoint(MineFreeState),
                            ),
                        ),
                        BoardRow(
                            listOf(
                                BoardPoint(MineFreeState),
                                BoardPoint(CoveredState(MineState)),
                                BoardPoint(MineFreeState),
                            ),
                        ),
                        BoardRow(
                            listOf(
                                BoardPoint(MineFreeState),
                                BoardPoint(MineFreeState),
                                BoardPoint(MineFreeState),
                            ),
                        ),
                    ),
                )

                rows.canUncover() shouldBe false
            }
        }

        context("특정 위치를 열면-tailRec") {
            it("해당 위치 주변의 가능한 곳까지 열림") {
                val rows = BoardRows(
                    listOf(
                        BoardRow(
                            listOf(
                                BoardPoint(CoveredState(MineFreeState)),
                                BoardPoint(CoveredState(MineFreeState)),
                                BoardPoint(CoveredState(MineFreeState)),
                            ),
                        ),
                        BoardRow(
                            listOf(
                                BoardPoint(CoveredState(NearMineState.one())),
                                BoardPoint(CoveredState(MineState)),
                                BoardPoint(CoveredState(NearMineState.one())),
                            ),
                        ),
                        BoardRow(
                            listOf(
                                BoardPoint(CoveredState(NearMineState.one())),
                                BoardPoint(CoveredState(NearMineState.one())),
                                BoardPoint(CoveredState(NearMineState.one())),
                            ),
                        ),
                    ),
                )

                rows.uncover(BoardPosition.of(1, 0, BoardArea.of(3, 3)), BoardArea.of(3, 3))

                rows shouldBe BoardRows(
                    listOf(
                        BoardRow(
                            listOf(
                                BoardPoint(MineFreeState),
                                BoardPoint(MineFreeState),
                                BoardPoint(MineFreeState),
                            ),
                        ),
                        BoardRow(
                            listOf(
                                BoardPoint(NearMineState.one()),
                                BoardPoint(CoveredState(MineState)),
                                BoardPoint(NearMineState.one()),
                            ),
                        ),
                        BoardRow(
                            listOf(
                                BoardPoint(CoveredState(NearMineState.one())),
                                BoardPoint(CoveredState(NearMineState.one())),
                                BoardPoint(CoveredState(NearMineState.one())),
                            ),
                        ),
                    ),
                )
            }
        }
    }
})
