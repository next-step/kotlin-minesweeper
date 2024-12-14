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
                    position = 11,
                    minePositions = listOf(1, 2, 10, 21, 22),
                    aroundMineCount = 5,
                ),
                AroundMineCountJudgeParam(
                    boardSize = boardSize,
                    position = 0,
                    minePositions = listOf(1, 10, 11),
                    aroundMineCount = 3,
                ),
                AroundMineCountJudgeParam(
                    boardSize = boardSize,
                    position = 99,
                    minePositions = listOf(89, 98),
                    aroundMineCount = 2,
                ),
                AroundMineCountJudgeParam(
                    boardSize = boardSize,
                    position = 9,
                    minePositions = listOf(18),
                    aroundMineCount = 1,
                ),
                AroundMineCountJudgeParam(
                    boardSize = boardSize,
                    position = 90,
                    minePositions = listOf(80, 79, 91, 99),
                    aroundMineCount = 2,
                ),
            )
        }
    }

    data class AroundMineCountJudgeParam(
        val boardSize: BoardSize,
        val position: Int,
        val minePositions: List<Int>,
        val aroundMineCount: Int,
    )

}
