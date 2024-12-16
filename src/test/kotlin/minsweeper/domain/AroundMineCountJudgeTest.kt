package minsweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class AroundMineCountJudgeTest {

    @ParameterizedTest
    @MethodSource("provideTestParams")
    fun `주변 지뢰 갯수를 테스트 한다`(param: AroundMineCountJudgeParam) {
        // given
        val judge = AroundMineCountJudge()

        // when
        val result = judge.judge(param.boardSize, param.position, param.minePositions)

        // then
        assertThat(result).isEqualTo(param.aroundMineCount)
    }

    companion object {
        @JvmStatic
        fun provideTestParams(): List<AroundMineCountJudgeParam> {
            val boardSize = BoardSize(10, 10)

            return listOf(
                AroundMineCountJudgeParam(
                    boardSize = boardSize,
                    position = Position(1, 1),
                    minePositions = listOf(
                        Position(0, 1),
                        Position(0, 2),
                        Position(1, 0),
                        Position(2, 1),
                        Position(2, 2),
                    ),
                    aroundMineCount = 5,
                ),
                AroundMineCountJudgeParam(
                    boardSize = boardSize,
                    position = Position(1, 1),
                    minePositions = listOf(
                        Position(0, 1),
                        Position(1, 0),
                        Position(2, 1),
                        Position(3, 3),
                    ),
                    aroundMineCount = 3,
                ),
                AroundMineCountJudgeParam(
                    boardSize = boardSize,
                    position = Position(0, 0),
                    minePositions = listOf(Position(0, 1), Position(1, 0), Position(1, 1)),
                    aroundMineCount = 3,
                ),
                AroundMineCountJudgeParam(
                    boardSize = boardSize,
                    position = Position(0, 0),
                    minePositions = listOf(Position(0, 1), Position(2, 1), Position(1, 2)),
                    aroundMineCount = 1,
                ),
                AroundMineCountJudgeParam(
                    boardSize = boardSize,
                    position = Position(9, 9),
                    minePositions = listOf(Position(8, 9), Position(9, 8)),
                    aroundMineCount = 2,
                ),
                AroundMineCountJudgeParam(
                    boardSize = boardSize,
                    position = Position(9, 9),
                    minePositions = listOf(Position(8, 8), Position(7, 8), Position(8, 7)),
                    aroundMineCount = 1,
                ),
                AroundMineCountJudgeParam(
                    boardSize = boardSize,
                    position = Position(0, 9),
                    minePositions = listOf(Position(1, 8), Position(2, 8)),
                    aroundMineCount = 1,
                ),
                AroundMineCountJudgeParam(
                    boardSize = boardSize,
                    position = Position(0, 9),
                    minePositions = listOf(Position(1, 8), Position(1, 9), Position(0, 8)),
                    aroundMineCount = 3,
                ),
                AroundMineCountJudgeParam(
                    boardSize = boardSize,
                    position = Position(9, 0),
                    minePositions = listOf(Position(8, 0), Position(7, 9), Position(9, 1), Position(9, 9)),
                    aroundMineCount = 2,
                ),
                AroundMineCountJudgeParam(
                    boardSize = boardSize,
                    position = Position(5, 5),
                    minePositions = listOf(
                        Position(5, 4),
                        Position(4, 4),
                        Position(6, 4),
                        Position(6, 6),
                    ),
                    aroundMineCount = 4,
                ),
                AroundMineCountJudgeParam(
                    boardSize = boardSize,
                    position = Position(5, 5),
                    minePositions = listOf(
                        Position(3, 4),
                        Position(4, 4),
                        Position(6, 4),
                        Position(6, 6),
                        Position(4, 5),
                        Position(7, 7),
                    ),
                    aroundMineCount = 4,
                ),
            )
        }
    }

    data class AroundMineCountJudgeParam(
        val boardSize: BoardSize,
        val position: Position,
        val minePositions: List<Position>,
        val aroundMineCount: Int,
    )

}
