package next.step.minesweeper.domain.board

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import next.step.minesweeper.domain.board.state.MineState
import next.step.minesweeper.domain.board.state.NearMineState
import next.step.minesweeper.domain.mine.MineCount
import next.step.minesweeper.domain.mine.MinePosition
import next.step.minesweeper.domain.mine.MinePositions
import org.junit.jupiter.api.assertThrows

class BoardTest : DescribeSpec({

    describe("Board") {
        val board = Board.mineFree(BoardHeight(3), BoardWidth(3))
        context("method") {
            it("너비 제공") {
                board.width() shouldBe 3
            }
            it("높이 제공") {
                board.height() shouldBe 3
            }
            it("넓이 제공") {
                board.area() shouldBe 9
            }
            it("지뢰 심기") {
                board.plantMines(
                    MinePositions(
                        setOf(
                            MinePosition(1, 0), MinePosition(0, 1),
                            MinePosition(2, 2)
                        )
                    )
                )

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
                    Board.covered(1, 1).plantMines(
                        MinePositions(
                            setOf(
                                MinePosition(1, 0), MinePosition(0, 1),
                                MinePosition(2, 2)
                            )
                        )
                    )
                }.shouldHaveMessage("지뢰 개수는 1개보다 많을 수 없습니다.")
            }
            it("board 높이를 벗어나게 지뢰를 심으면 예외 발생") {
                assertThrows<IllegalArgumentException> {
                    Board.covered(3, 3).plantMines(
                        MinePositions(setOf(MinePosition(1, 3)))
                    )
                }.shouldHaveMessage("지뢰 y 위치는 3 보다 작아야 합니다.")
            }
            it("board 너비를 벗어나게 지뢰를 심으면 예외 발생") {
                assertThrows<IllegalArgumentException> {
                    Board.covered(3, 3).plantMines(
                        MinePositions(setOf(MinePosition(3, 1)))
                    )
                }.shouldHaveMessage("지뢰 x 위치는 3 보다 작아야 합니다.")
            }
        }
    }
})
