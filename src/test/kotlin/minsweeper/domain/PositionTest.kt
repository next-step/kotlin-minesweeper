package minsweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class PositionTest {

    @ParameterizedTest
    @MethodSource("provideLeftTestParam")
    fun `왼쪽 포지션을 알 수 있다`(param: PositionDefaultTestParam) {
        // given

        // when
        val result = param.position.left()

        // then
        assertThat(result).isEqualTo(param.result)
    }

    @ParameterizedTest
    @MethodSource("provideRightTestParam")
    fun `오른쪽 포지션을 알 수 있다`(param: PositionWithWidthTestParam) {
        // given

        // when
        val result = param.position.right(param.width)

        // then
        assertThat(result).isEqualTo(param.result)
    }

    @ParameterizedTest
    @MethodSource("provideTopLeftTestParam")
    fun `왼쪽 위 포지션을 알 수 있다`(param: PositionDefaultTestParam) {
        // given

        // when
        val result = param.position.topLeft()

        // then
        assertThat(result).isEqualTo(param.result)
    }

    @ParameterizedTest
    @MethodSource("provideTopCenterTestParam")
    fun `가운데 위 포지션을 알 수 있다`(param: PositionDefaultTestParam) {
        // given

        // when
        val result = param.position.topCenter()

        // then
        assertThat(result).isEqualTo(param.result)
    }

    @ParameterizedTest
    @MethodSource("provideTopRightTestParam")
    fun `오른쪽 위 포지션을 알 수 있다`(param: PositionWithWidthTestParam) {
        // given

        // when
        val result = param.position.topRight(param.width)

        // then
        assertThat(result).isEqualTo(param.result)
    }

    @ParameterizedTest
    @MethodSource("provideBottomLeftTestParam")
    fun `왼쪽 아래 포지션을 알 수 있다`(param: PositionWithHeightTestParam) {
        // given

        // when
        val result = param.position.bottomLeft(param.height)

        // then
        assertThat(result).isEqualTo(param.result)
    }

    @ParameterizedTest
    @MethodSource("provideBottomCenterTestParam")
    fun `가운데 아래 포지션을 알 수 있다`(param: PositionWithHeightTestParam) {
        // given

        // when
        val result = param.position.bottomCenter(param.height)

        // then
        assertThat(result).isEqualTo(param.result)
    }

    @ParameterizedTest
    @MethodSource("provideBottomRightTestParam")
    fun `오른쪽 아래 포지션을 알 수 있다`(param: PositionWithBoardSizeTestParam) {
        // given

        // when
        val result = param.position.bottomRight(param.boardSize.width, param.boardSize.height)

        // then
        assertThat(result).isEqualTo(param.result)
    }

    companion object {
        @JvmStatic
        fun provideLeftTestParam() = listOf(
            PositionDefaultTestParam(Position(2, 0), null),
            PositionDefaultTestParam(Position(3, 10), Position(3, 9)),
        )

        @JvmStatic
        fun provideRightTestParam() = listOf(
            PositionWithWidthTestParam(10, Position(2, 9), null),
            PositionWithWidthTestParam(10, Position(3, 8), Position(3, 9)),
        )

        @JvmStatic
        fun provideTopLeftTestParam() = listOf(
            PositionDefaultTestParam(Position(0, 1), null),
            PositionDefaultTestParam(Position(1, 0), null),
            PositionDefaultTestParam(Position(2, 3), Position(1, 2)),
        )

        @JvmStatic
        fun provideTopCenterTestParam() = listOf(
            PositionDefaultTestParam(Position(0, 1), null),
            PositionDefaultTestParam(Position(1, 0), Position(0, 0)),
            PositionDefaultTestParam(Position(2, 3), Position(1, 3)),
        )

        @JvmStatic
        fun provideTopRightTestParam() = listOf(
            PositionWithWidthTestParam(10, Position(1, 9), null),
            PositionWithWidthTestParam(10, Position(0, 2), null),
            PositionWithWidthTestParam(10, Position(1, 8), Position(0, 9)),
        )

        @JvmStatic
        fun provideBottomLeftTestParam() = listOf(
            PositionWithHeightTestParam(10, Position(9, 9), null),
            PositionWithHeightTestParam(10, Position(8, 0), null),
            PositionWithHeightTestParam(10, Position(7, 6), Position(8, 5)),
        )

        @JvmStatic
        fun provideBottomCenterTestParam() = listOf(
            PositionWithHeightTestParam(10, Position(9, 9), null),
            PositionWithHeightTestParam(10, Position(8, 0), Position(9, 0)),
            PositionWithHeightTestParam(10, Position(7, 6), Position(8, 6)),
        )

        @JvmStatic
        fun provideBottomRightTestParam() = listOf(
            PositionWithBoardSizeTestParam(BoardSize(10, 10), Position(9, 6), null),
            PositionWithBoardSizeTestParam(BoardSize(10, 10), Position(9, 9), null),
            PositionWithBoardSizeTestParam(BoardSize(10, 10), Position(7, 6), Position(8, 7)),
        )
    }

    data class PositionDefaultTestParam(
        val position: Position,
        val result: Position?,
    )

    data class PositionWithWidthTestParam(
        val width: Int,
        val position: Position,
        val result: Position?,
    )

    data class PositionWithHeightTestParam(
        val height: Int,
        val position: Position,
        val result: Position?,
    )

    data class PositionWithBoardSizeTestParam(
        val boardSize: BoardSize,
        val position: Position,
        val result: Position?,
    )

}
