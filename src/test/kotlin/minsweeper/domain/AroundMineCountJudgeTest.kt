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
        val result = judge.judge(param.boardSize, param.coordinate, param.mineCoordinates)

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
                    coordinate = Coordinate.of(1, 1),
                    mineCoordinates = listOf(
                        Coordinate.of(0, 1),
                        Coordinate.of(0, 2),
                        Coordinate.of(1, 0),
                        Coordinate.of(2, 1),
                        Coordinate.of(2, 2),
                    ),
                    aroundMineCount = 5,
                ),
                AroundMineCountJudgeParam(
                    boardSize = boardSize,
                    coordinate = Coordinate.of(1, 1),
                    mineCoordinates = listOf(
                        Coordinate.of(0, 1),
                        Coordinate.of(1, 0),
                        Coordinate.of(2, 1),
                        Coordinate.of(3, 3),
                    ),
                    aroundMineCount = 3,
                ),
                AroundMineCountJudgeParam(
                    boardSize = boardSize,
                    coordinate = Coordinate.of(0, 0),
                    mineCoordinates = listOf(Coordinate.of(0, 1), Coordinate.of(1, 0), Coordinate.of(1, 1)),
                    aroundMineCount = 3,
                ),
                AroundMineCountJudgeParam(
                    boardSize = boardSize,
                    coordinate = Coordinate.of(0, 0),
                    mineCoordinates = listOf(Coordinate.of(0, 1), Coordinate.of(2, 1), Coordinate.of(1, 2)),
                    aroundMineCount = 1,
                ),
                AroundMineCountJudgeParam(
                    boardSize = boardSize,
                    coordinate = Coordinate.of(9, 9),
                    mineCoordinates = listOf(Coordinate.of(8, 9), Coordinate.of(9, 8)),
                    aroundMineCount = 2,
                ),
                AroundMineCountJudgeParam(
                    boardSize = boardSize,
                    coordinate = Coordinate.of(9, 9),
                    mineCoordinates = listOf(Coordinate.of(8, 8), Coordinate.of(7, 8), Coordinate.of(8, 7)),
                    aroundMineCount = 1,
                ),
                AroundMineCountJudgeParam(
                    boardSize = boardSize,
                    coordinate = Coordinate.of(0, 9),
                    mineCoordinates = listOf(Coordinate.of(1, 8), Coordinate.of(2, 8)),
                    aroundMineCount = 1,
                ),
                AroundMineCountJudgeParam(
                    boardSize = boardSize,
                    coordinate = Coordinate.of(0, 9),
                    mineCoordinates = listOf(Coordinate.of(1, 8), Coordinate.of(1, 9), Coordinate.of(0, 8)),
                    aroundMineCount = 3,
                ),
                AroundMineCountJudgeParam(
                    boardSize = boardSize,
                    coordinate = Coordinate.of(9, 0),
                    mineCoordinates = listOf(Coordinate.of(8, 0), Coordinate.of(7, 9), Coordinate.of(9, 1), Coordinate.of(9, 9)),
                    aroundMineCount = 2,
                ),
                AroundMineCountJudgeParam(
                    boardSize = boardSize,
                    coordinate = Coordinate.of(5, 5),
                    mineCoordinates = listOf(
                        Coordinate.of(5, 4),
                        Coordinate.of(4, 4),
                        Coordinate.of(6, 4),
                        Coordinate.of(6, 6),
                    ),
                    aroundMineCount = 4,
                ),
                AroundMineCountJudgeParam(
                    boardSize = boardSize,
                    coordinate = Coordinate.of(5, 5),
                    mineCoordinates = listOf(
                        Coordinate.of(3, 4),
                        Coordinate.of(4, 4),
                        Coordinate.of(6, 4),
                        Coordinate.of(6, 6),
                        Coordinate.of(4, 5),
                        Coordinate.of(7, 7),
                    ),
                    aroundMineCount = 4,
                ),
            )
        }
    }

    data class AroundMineCountJudgeParam(
        val boardSize: BoardSize,
        val coordinate: Coordinate,
        val mineCoordinates: List<Coordinate>,
        val aroundMineCount: Int,
    )

}
