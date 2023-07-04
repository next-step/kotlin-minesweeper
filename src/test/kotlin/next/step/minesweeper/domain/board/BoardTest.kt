package next.step.minesweeper.domain.board

import io.kotest.assertions.fail
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import next.step.minesweeper.domain.board.state.CoveredState
import next.step.minesweeper.domain.board.state.MineState
import next.step.minesweeper.domain.board.state.NearMineState
import next.step.minesweeper.domain.mine.MineCount
import next.step.minesweeper.domain.mine.generator.MineGenerator
import next.step.minesweeper.domain.position.Position
import org.junit.jupiter.api.assertThrows

class BoardTest : DescribeSpec({

    describe("Board") {
        val area = BoardArea(BoardHeight(3), BoardWidth(3))
        val mineGenerator = TestMineGenerator(
            BoardPosition(1, 0),
            BoardPosition(0, 1),
            BoardPosition(2, 2),
        )
        context("생성") {
            it("지뢰 생성기를 통해 주어진 개수만큼 지뢰를 심음") {
                val board = Board.of(
                    area,
                    mineGenerator,
                    MineCount(3),
                )

                board.points() shouldBe listOf(
                    listOf(
                        BoardPoint(CoveredState(NearMineState(MineCount(2)))),
                        BoardPoint(CoveredState(MineState)),
                        BoardPoint(CoveredState(NearMineState(MineCount(1)))),
                    ),
                    listOf(
                        BoardPoint(CoveredState(MineState)),
                        BoardPoint(CoveredState(NearMineState(MineCount(3)))),
                        BoardPoint(CoveredState(NearMineState(MineCount(2)))),
                    ),
                    listOf(
                        BoardPoint(CoveredState(NearMineState(MineCount(1)))),
                        BoardPoint(CoveredState(NearMineState(MineCount(2)))),
                        BoardPoint(CoveredState(MineState)),
                    ),
                )
            }

            context("지뢰를 board보다 더 많이 생성하려고 하면") {
                it("예외 발생") {
                    assertThrows<IllegalArgumentException> {
                        Board.of(
                            area,
                            mineGenerator,
                            MineCount(10),
                        )
                    }.shouldHaveMessage("9개보다 더 많을 수 없습니다.")
                }
            }
        }

        context("method") {
            it("uncover할 수 없으면 게임 success로 바로 종료") {
                val smallArea = BoardArea(BoardHeight(2), BoardWidth(2))
                val board = Board.of(
                    smallArea,
                    TestMineGenerator(
                        BoardPosition(1, 0),
                        BoardPosition(0, 1),
                        BoardPosition(0, 0),
                        BoardPosition(1, 1),
                    ),
                    MineCount(4),
                )

                board.play(
                    { Position(1, 1) },
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
                    { fail("호출 안됨") },
                    {},
                )
            }
            it("지뢰찾기 플레이 중 지뢰만 남기고 uncover하면 success로 종료됨") {
                val board = Board.of(
                    area,
                    TestMineGenerator(
                        BoardPosition(1, 0),
                        BoardPosition(0, 1),
                        BoardPosition(2, 2),
                    ),
                    MineCount(3),
                )
                val selects = mutableListOf(
                    Position(0, 0),
                    Position(0, 2),
                    Position(1, 1),
                    Position(1, 2),
                    Position(2, 0),
                    Position(2, 1),
                )

                board.play(
                    { selects.removeAt(0) },
                    {},
                    {
                        it shouldBe listOf(
                            listOf(
                                BoardPoint(NearMineState(MineCount(2))),
                                BoardPoint(CoveredState(MineState)),
                                BoardPoint(NearMineState(MineCount(1))),
                            ),
                            listOf(
                                BoardPoint(CoveredState(MineState)),
                                BoardPoint(NearMineState(MineCount(3))),
                                BoardPoint(NearMineState(MineCount(2))),
                            ),
                            listOf(
                                BoardPoint(NearMineState(MineCount(1))),
                                BoardPoint(NearMineState(MineCount(2))),
                                BoardPoint(CoveredState(MineState)),
                            ),
                        )
                    },
                    { fail("호출 안됨") },
                    { },
                )
            }
            it("지뢰찾기 플레이 중 지뢰를 uncover하면 fail로 종료됨") {
                val board = Board.of(
                    area,
                    TestMineGenerator(
                        BoardPosition(1, 0),
                        BoardPosition(0, 1),
                        BoardPosition(2, 2),
                    ),
                    MineCount(3),
                )
                val selects = mutableListOf(
                    Position(2, 2),
                )

                board.play(
                    { selects.removeAt(0) },
                    { },
                    { fail("호출 안됨") },
                    {
                        it shouldBe listOf(
                            listOf(
                                BoardPoint(CoveredState(NearMineState(MineCount(2)))),
                                BoardPoint(CoveredState(MineState)),
                                BoardPoint(CoveredState(NearMineState(MineCount(1)))),
                            ),
                            listOf(
                                BoardPoint(CoveredState(MineState)),
                                BoardPoint(CoveredState(NearMineState(MineCount(3)))),
                                BoardPoint(CoveredState(NearMineState(MineCount(2)))),
                            ),
                            listOf(
                                BoardPoint(CoveredState(NearMineState(MineCount(1)))),
                                BoardPoint(CoveredState(NearMineState(MineCount(2)))),
                                BoardPoint(MineState),
                            ),
                        )
                    },
                    { },
                )
            }
        }
    }
})

class TestMineGenerator(private vararg val positions: BoardPosition) : MineGenerator {
    override fun generate(area: BoardArea, count: MineCount): BoardPositions =
        BoardPositions(positions.toSet())
}
