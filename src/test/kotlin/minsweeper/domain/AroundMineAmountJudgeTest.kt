package minsweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class AroundMineAmountJudgeTest {

    @ParameterizedTest
    @MethodSource("provideAroundMineAmountTestParam")
    fun `주변 지뢰 갯수를 반환해야 한다`(param: AroundMineAmountTestParam) {
        // given
        val judge = AroundMineAmountJudge()
        val boardSize = BoardSize(10, 10)

        // when
        val result = judge.judge(
            coordinate = param.coordinate,
            mineCoordinates = param.mineCoordinates,
            boardSize = boardSize,
        )

        // then
        assertThat(result).isEqualTo(param.aroundMineAmount)
    }

    companion object {
        @JvmStatic
        fun provideAroundMineAmountTestParam(): List<AroundMineAmountTestParam> = listOf(
            AroundMineAmountTestParam(
                coordinate = Coordinate(1, 1),
                mineCoordinates = listOf(Coordinate(1, 2), Coordinate(2, 1), Coordinate(3, 1)),
                aroundMineAmount = 2,
            ),
            AroundMineAmountTestParam(
                coordinate = Coordinate(1, 1),
                mineCoordinates = listOf(Coordinate(2, 2), Coordinate(1, 3), Coordinate(3, 1)),
                aroundMineAmount = 1,
            ),
            AroundMineAmountTestParam(
                coordinate = Coordinate(1, 10),
                mineCoordinates = listOf(Coordinate(1, 9), Coordinate(2, 9), Coordinate(3, 1)),
                aroundMineAmount = 2,
            ),
            AroundMineAmountTestParam(
                coordinate = Coordinate(1, 10),
                mineCoordinates = listOf(Coordinate(2, 10), Coordinate(1, 8), Coordinate(3, 1)),
                aroundMineAmount = 1,
            ),
            AroundMineAmountTestParam(
                coordinate = Coordinate(10, 1),
                mineCoordinates = listOf(Coordinate(9, 2), Coordinate(9, 1), Coordinate(10, 2)),
                aroundMineAmount = 3,
            ),
            AroundMineAmountTestParam(
                coordinate = Coordinate(10, 1),
                mineCoordinates = listOf(Coordinate(9, 1), Coordinate(10, 3), Coordinate(9, 3)),
                aroundMineAmount = 1,
            ),
            AroundMineAmountTestParam(
                coordinate = Coordinate(10, 10),
                mineCoordinates = listOf(Coordinate(9, 9), Coordinate(10, 8), Coordinate(8, 10)),
                aroundMineAmount = 1,
            ),
            AroundMineAmountTestParam(
                coordinate = Coordinate(10, 10),
                mineCoordinates = listOf(Coordinate(9, 10), Coordinate(10, 9), Coordinate(8, 10)),
                aroundMineAmount = 2,
            ),
            AroundMineAmountTestParam(
                coordinate = Coordinate(5, 5),
                mineCoordinates = listOf(
                    Coordinate(5, 6),
                    Coordinate(6, 5),
                    Coordinate(4, 3),
                    Coordinate(3, 7),
                    Coordinate(3, 3)
                ),
                aroundMineAmount = 2,
            ),
            AroundMineAmountTestParam(
                coordinate = Coordinate(5, 5),
                mineCoordinates = listOf(Coordinate(4, 6), Coordinate(6, 4), Coordinate(4, 4), Coordinate(4, 7)),
                aroundMineAmount = 3,
            ),
        )
    }

    data class AroundMineAmountTestParam(
        val coordinate: Coordinate,
        val mineCoordinates: List<Coordinate>,
        val aroundMineAmount: Int,
    )

}