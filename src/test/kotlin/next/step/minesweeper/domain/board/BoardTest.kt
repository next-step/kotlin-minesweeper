package next.step.minesweeper.domain.board

import io.kotest.assertions.fail
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import next.step.minesweeper.domain.board.state.CoveredState
import next.step.minesweeper.domain.board.state.MineFreeState
import next.step.minesweeper.domain.board.state.MineState
import next.step.minesweeper.domain.board.state.NearMineState
import next.step.minesweeper.domain.mine.MineCount
import next.step.minesweeper.domain.mine.MinePosition
import next.step.minesweeper.domain.mine.MinePositions
import next.step.minesweeper.domain.position.Position
import org.junit.jupiter.api.assertThrows

class BoardTest : DescribeSpec({

    describe("Board") {
        context("method") {
            it("지뢰 심기") {
                val board = minePlantedBoard()

                board.points() shouldBe listOf(
                    listOf(
                        BoardPoint(NearMineState(MineCount(2))),
                        BoardPoint(MineState),
                        BoardPoint(NearMineState(MineCount(1)))
                    ),
                    listOf(
                        BoardPoint(MineState),
                        BoardPoint(NearMineState(MineCount(3))),
                        BoardPoint(NearMineState(MineCount(2)))
                    ),
                    listOf(
                        BoardPoint(NearMineState(MineCount(1))),
                        BoardPoint(NearMineState(MineCount(2))),
                        BoardPoint(MineState)
                    ),
                )
            }
            it("board 전체 크기보다 지뢰를 많이 심으면 예외 발생") {
                assertThrows<IllegalArgumentException> {
                    Board.mineFree(BoardArea.of(1, 1)).plantMines(
                        MinePositions(
                            setOf(
                                MinePosition(1, 0), MinePosition(0, 1),
                                MinePosition(2, 2)
                            )
                        )
                    )
                }.shouldHaveMessage("1개보다 더 넣을 수 없습니다.")
            }
            it("board 높이를 벗어나게 지뢰를 심으면 예외 발생") {
                assertThrows<IllegalArgumentException> {
                    Board.mineFree(BoardArea.of(3, 3)).plantMines(
                        MinePositions(setOf(MinePosition(1, 3)))
                    )
                }.shouldHaveMessage("y 위치는 0보다 크고, 3 보다 작아야 합니다.")
            }
            it("board 너비를 벗어나게 지뢰를 심으면 예외 발생") {
                assertThrows<IllegalArgumentException> {
                    Board.mineFree(BoardArea.of(3, 3)).plantMines(
                        MinePositions(setOf(MinePosition(3, 1)))
                    )
                }.shouldHaveMessage("x 위치는 0보다 크고, 3 보다 작아야 합니다.")
            }
            it("덮으면 모두 CoveredState가 됨") {
                val freeBoard = Board.mineFree(BoardArea(BoardHeight(3), BoardWidth(3)))

                freeBoard.cover()

                freeBoard.points() shouldBe listOf(
                    listOf(
                        BoardPoint(CoveredState(MineFreeState)),
                        BoardPoint(CoveredState(MineFreeState)),
                        BoardPoint(CoveredState(MineFreeState))
                    ),
                    listOf(
                        BoardPoint(CoveredState(MineFreeState)),
                        BoardPoint(CoveredState(MineFreeState)),
                        BoardPoint(CoveredState(MineFreeState))
                    ),
                    listOf(
                        BoardPoint(CoveredState(MineFreeState)),
                        BoardPoint(CoveredState(MineFreeState)),
                        BoardPoint(CoveredState(MineFreeState))
                    ),
                )
            }
            it("uncover할 수 없으면 게임 success로 바로 종료") {
                val board = mineFullPlantedBoard()

                board.play(
                    { Position(1, 1) }, {},
                    { fail("호출 안됨") },
                    {
                        it shouldBe listOf(
                            listOf(
                                BoardPoint(CoveredState(MineState)),
                                BoardPoint(CoveredState(MineState)),
                            ),
                            listOf(
                                BoardPoint(CoveredState(MineState)),
                                BoardPoint(CoveredState(MineState)),
                            ),
                        )
                    },
                    { fail("호출 안됨") }
                )
            }
            it("지뢰찾기 플레이 중 지뢰만 남기고 uncover하면 success로 종료됨") {
                val board = mineCoveredBoard()
                val selects = mutableListOf(
                    Position(0, 0),
                    Position(0, 2),
                    Position(1, 1),
                    Position(1, 2),
                    Position(2, 0),
                    Position(2, 1)
                )

                board.play(
                    { selects.removeAt(0) },
                    { }, {},
                    {
                        it shouldBe listOf(
                            listOf(
                                BoardPoint(NearMineState(MineCount(2))),
                                BoardPoint(CoveredState(MineState)),
                                BoardPoint(NearMineState(MineCount(1)))
                            ),
                            listOf(
                                BoardPoint(CoveredState(MineState)),
                                BoardPoint(NearMineState(MineCount(3))),
                                BoardPoint(NearMineState(MineCount(2)))
                            ),
                            listOf(
                                BoardPoint(NearMineState(MineCount(1))),
                                BoardPoint(NearMineState(MineCount(2))),
                                BoardPoint(CoveredState(MineState)),
                            ),
                        )
                    },
                    { fail("호출 안됨") }
                )
            }
            it("지뢰찾기 플레이 중 지뢰를 uncover하면 fail로 종료됨") {
                val board = mineCoveredBoard()
                val selects = mutableListOf(
                    Position(2, 2)
                )

                board.play(
                    { selects.removeAt(0) }, { }, { },
                    { fail("호출 안됨") },
                    {
                        it shouldBe listOf(
                            listOf(
                                BoardPoint(CoveredState(NearMineState(MineCount(2)))),
                                BoardPoint(CoveredState(MineState)),
                                BoardPoint(CoveredState(NearMineState(MineCount(1))))
                            ),
                            listOf(
                                BoardPoint(CoveredState(MineState)),
                                BoardPoint(CoveredState(NearMineState(MineCount(3)))),
                                BoardPoint(CoveredState(NearMineState(MineCount(2))))
                            ),
                            listOf(
                                BoardPoint(CoveredState(NearMineState(MineCount(1)))),
                                BoardPoint(CoveredState(NearMineState(MineCount(2)))),
                                BoardPoint(MineState),
                            ),
                        )
                    }
                )
            }
        }
    }
})

private fun minePlantedBoard(): Board {
    val board = Board.mineFree(BoardArea(BoardHeight(3), BoardWidth(3)))

    board.plantMines(
        MinePositions(
            setOf(
                MinePosition(1, 0), MinePosition(0, 1),
                MinePosition(2, 2)
            )
        )
    )
    return board
}

private fun mineCoveredBoard(): Board {
    val board = minePlantedBoard()
    board.cover()
    return board
}

private fun mineFullPlantedBoard(): Board {
    val board = Board.mineFree(BoardArea(BoardHeight(2), BoardWidth(2)))

    board.plantMines(
        MinePositions(
            setOf(
                MinePosition(1, 0), MinePosition(0, 1),
                MinePosition(0, 0), MinePosition(1, 1),
            )
        )
    )
    board.cover()
    return board
}
