package minsweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class CoordinateTest {

    @ParameterizedTest
    @MethodSource("provideLeftTestParam")
    fun `왼쪽 좌표를 알 수 있다`(param: CoordinateDefaultTestParam) {
        // given

        // when
        val result = param.coordinate.left()

        // then
        assertThat(result).isEqualTo(param.result)
    }

    @ParameterizedTest
    @MethodSource("provideRightTestParam")
    fun `오른쪽 좌표를 알 수 있다`(param: CoordinateWithWidthTestParam) {
        // given

        // when
        val result = param.coordinate.right(param.width)

        // then
        assertThat(result).isEqualTo(param.result)
    }

    @ParameterizedTest
    @MethodSource("provideTopLeftTestParam")
    fun `왼쪽 위 좌표를 알 수 있다`(param: CoordinateDefaultTestParam) {
        // given

        // when
        val result = param.coordinate.topLeft()

        // then
        assertThat(result).isEqualTo(param.result)
    }

    @ParameterizedTest
    @MethodSource("provideTopCenterTestParam")
    fun `가운데 위 좌표를 알 수 있다`(param: CoordinateDefaultTestParam) {
        // given

        // when
        val result = param.coordinate.topCenter()

        // then
        assertThat(result).isEqualTo(param.result)
    }

    @ParameterizedTest
    @MethodSource("provideTopRightTestParam")
    fun `오른쪽 위 좌표를 알 수 있다`(param: CoordinateWithWidthTestParam) {
        // given

        // when
        val result = param.coordinate.topRight(param.width)

        // then
        assertThat(result).isEqualTo(param.result)
    }

    @ParameterizedTest
    @MethodSource("provideBottomLeftTestParam")
    fun `왼쪽 아래 좌표를 알 수 있다`(param: CoordinateWithHeightTestParam) {
        // given

        // when
        val result = param.coordinate.bottomLeft(param.height)

        // then
        assertThat(result).isEqualTo(param.result)
    }

    @ParameterizedTest
    @MethodSource("provideBottomCenterTestParam")
    fun `가운데 아래 좌표를 알 수 있다`(param: CoordinateWithHeightTestParam) {
        // given

        // when
        val result = param.coordinate.bottomCenter(param.height)

        // then
        assertThat(result).isEqualTo(param.result)
    }

    @ParameterizedTest
    @MethodSource("provideBottomRightTestParam")
    fun `오른쪽 아래 좌표를 알 수 있다`(param: CoordinateWithBoardSizeTestParam) {
        // given

        // when
        val result = param.coordinate.bottomRight(param.boardSize.width, param.boardSize.height)

        // then
        assertThat(result).isEqualTo(param.result)
    }

    companion object {
        @JvmStatic
        fun provideLeftTestParam() = listOf(
            CoordinateDefaultTestParam(Coordinate(2, 0), null),
            CoordinateDefaultTestParam(Coordinate(3, 10), Coordinate(3, 9)),
        )

        @JvmStatic
        fun provideRightTestParam() = listOf(
            CoordinateWithWidthTestParam(10, Coordinate(2, 9), null),
            CoordinateWithWidthTestParam(10, Coordinate(3, 8), Coordinate(3, 9)),
        )

        @JvmStatic
        fun provideTopLeftTestParam() = listOf(
            CoordinateDefaultTestParam(Coordinate(0, 1), null),
            CoordinateDefaultTestParam(Coordinate(1, 0), null),
            CoordinateDefaultTestParam(Coordinate(2, 3), Coordinate(1, 2)),
        )

        @JvmStatic
        fun provideTopCenterTestParam() = listOf(
            CoordinateDefaultTestParam(Coordinate(0, 1), null),
            CoordinateDefaultTestParam(Coordinate(1, 0), Coordinate(0, 0)),
            CoordinateDefaultTestParam(Coordinate(2, 3), Coordinate(1, 3)),
        )

        @JvmStatic
        fun provideTopRightTestParam() = listOf(
            CoordinateWithWidthTestParam(10, Coordinate(1, 9), null),
            CoordinateWithWidthTestParam(10, Coordinate(0, 2), null),
            CoordinateWithWidthTestParam(10, Coordinate(1, 8), Coordinate(0, 9)),
        )

        @JvmStatic
        fun provideBottomLeftTestParam() = listOf(
            CoordinateWithHeightTestParam(10, Coordinate(9, 9), null),
            CoordinateWithHeightTestParam(10, Coordinate(8, 0), null),
            CoordinateWithHeightTestParam(10, Coordinate(7, 6), Coordinate(8, 5)),
        )

        @JvmStatic
        fun provideBottomCenterTestParam() = listOf(
            CoordinateWithHeightTestParam(10, Coordinate(9, 9), null),
            CoordinateWithHeightTestParam(10, Coordinate(8, 0), Coordinate(9, 0)),
            CoordinateWithHeightTestParam(10, Coordinate(7, 6), Coordinate(8, 6)),
        )

        @JvmStatic
        fun provideBottomRightTestParam() = listOf(
            CoordinateWithBoardSizeTestParam(BoardSize(10, 10), Coordinate(9, 6), null),
            CoordinateWithBoardSizeTestParam(BoardSize(10, 10), Coordinate(9, 9), null),
            CoordinateWithBoardSizeTestParam(BoardSize(10, 10), Coordinate(7, 6), Coordinate(8, 7)),
        )
    }

    data class CoordinateDefaultTestParam(
        val coordinate: Coordinate,
        val result: Coordinate?,
    )

    data class CoordinateWithWidthTestParam(
        val width: Int,
        val coordinate: Coordinate,
        val result: Coordinate?,
    )

    data class CoordinateWithHeightTestParam(
        val height: Int,
        val coordinate: Coordinate,
        val result: Coordinate?,
    )

    data class CoordinateWithBoardSizeTestParam(
        val boardSize: BoardSize,
        val coordinate: Coordinate,
        val result: Coordinate?,
    )

}
