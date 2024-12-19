package minsweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

class CoordinateTest {

    @Test
    fun `좌표가 음수이면 에러를 던져야 한다`() {
        // given

        // when
        val result = assertThrows<IllegalArgumentException> { Coordinate.of(-1, -1) }

        // then
        assertThat(result.message).isEqualTo("좌표는 음수일 수 없습니다")
    }

    @ParameterizedTest
    @ValueSource(strings = ["1, 2, 3", "123", "abc"])
    fun `올바른 문자열 형태로 넣지않으면 에러를 던져야 한다`(input: String) {
        // given

        // when
        val result = assertThrows<IllegalArgumentException> { Coordinate.of(input) }

        // then
        assertThat(result.message).isEqualTo("올바른 형식이 아닙니다")
    }

    @Test
    fun `문자열을 넣으면 Coordinate를 만들 수 있다`() {
        // given
        val input = "1, 2"

        // when
        val result = Coordinate.of(input)

        // then
        assertThat(result.row).isEqualTo(0)
        assertThat(result.column).isEqualTo(1)
    }

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
            CoordinateDefaultTestParam(Coordinate.of(2, 0), null),
            CoordinateDefaultTestParam(Coordinate.of(3, 10), Coordinate.of(3, 9)),
        )

        @JvmStatic
        fun provideRightTestParam() = listOf(
            CoordinateWithWidthTestParam(10, Coordinate.of(2, 9), null),
            CoordinateWithWidthTestParam(10, Coordinate.of(3, 8), Coordinate.of(3, 9)),
        )

        @JvmStatic
        fun provideTopLeftTestParam() = listOf(
            CoordinateDefaultTestParam(Coordinate.of(0, 1), null),
            CoordinateDefaultTestParam(Coordinate.of(1, 0), null),
            CoordinateDefaultTestParam(Coordinate.of(2, 3), Coordinate.of(1, 2)),
        )

        @JvmStatic
        fun provideTopCenterTestParam() = listOf(
            CoordinateDefaultTestParam(Coordinate.of(0, 1), null),
            CoordinateDefaultTestParam(Coordinate.of(1, 0), Coordinate.of(0, 0)),
            CoordinateDefaultTestParam(Coordinate.of(2, 3), Coordinate.of(1, 3)),
        )

        @JvmStatic
        fun provideTopRightTestParam() = listOf(
            CoordinateWithWidthTestParam(10, Coordinate.of(1, 9), null),
            CoordinateWithWidthTestParam(10, Coordinate.of(0, 2), null),
            CoordinateWithWidthTestParam(10, Coordinate.of(1, 8), Coordinate.of(0, 9)),
        )

        @JvmStatic
        fun provideBottomLeftTestParam() = listOf(
            CoordinateWithHeightTestParam(10, Coordinate.of(9, 9), null),
            CoordinateWithHeightTestParam(10, Coordinate.of(8, 0), null),
            CoordinateWithHeightTestParam(10, Coordinate.of(7, 6), Coordinate.of(8, 5)),
        )

        @JvmStatic
        fun provideBottomCenterTestParam() = listOf(
            CoordinateWithHeightTestParam(10, Coordinate.of(9, 9), null),
            CoordinateWithHeightTestParam(10, Coordinate.of(8, 0), Coordinate.of(9, 0)),
            CoordinateWithHeightTestParam(10, Coordinate.of(7, 6), Coordinate.of(8, 6)),
        )

        @JvmStatic
        fun provideBottomRightTestParam() = listOf(
            CoordinateWithBoardSizeTestParam(BoardSize(10, 10), Coordinate.of(9, 6), null),
            CoordinateWithBoardSizeTestParam(BoardSize(10, 10), Coordinate.of(9, 9), null),
            CoordinateWithBoardSizeTestParam(BoardSize(10, 10), Coordinate.of(7, 6), Coordinate.of(8, 7)),
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
