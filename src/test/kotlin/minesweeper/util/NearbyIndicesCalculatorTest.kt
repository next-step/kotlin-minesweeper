package minesweeper.util

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class NearbyIndicesCalculatorTest {

    @ParameterizedTest(name = "index:{0}, width:{1}, height:{2}, expected:{3}")
    @MethodSource(
        "너비가 1 인 케이스",
        "높이가 1 인 케이스",
        "보드의 크기가 3x3 인 케이스",
        "보드의 크기가 6x3 인 케이스",
        "보드의 크기가 3x6 인 케이스",
    )
    fun `인접한 인덱스 목록을 얻을 수 있다`(index: Int, width: Int, height: Int, expectedNearbyIndices: List<Int>) {
        // when
        val result = NearbyIndicesCalculator.getNearbyIndices(index, width, height)

        // then
        Assertions.assertThat(result).containsExactlyElementsOf(expectedNearbyIndices)
    }

    companion object {

        @JvmStatic
        fun `너비가 1 인 케이스`() = Stream.of(
            Arguments.of(0, 1, 3, listOf(1)),
            Arguments.of(1, 1, 3, listOf(0, 2)),
            Arguments.of(2, 1, 3, listOf(1))
        )

        @JvmStatic
        fun `높이가 1 인 케이스`() = Stream.of(
            Arguments.of(0, 6, 1, listOf(1)),
            Arguments.of(3, 6, 1, listOf(2, 4)),
            Arguments.of(5, 6, 1, listOf(4))
        )

        @JvmStatic
        fun `보드의 크기가 3x3 인 케이스`() = Stream.of(
            Arguments.of(0, 3, 3, listOf(1, 3, 4)),
            Arguments.of(2, 3, 3, listOf(1, 4, 5)),
            Arguments.of(8, 3, 3, listOf(4, 5, 7))
        )

        @JvmStatic
        fun `보드의 크기가 6x3 인 케이스`() = Stream.of(
            Arguments.of(0, 6, 3, listOf(1, 6, 7)),
            Arguments.of(5, 6, 3, listOf(4, 10, 11)),
            Arguments.of(17, 6, 3, listOf(10, 11, 16))
        )
        @JvmStatic
        fun `보드의 크기가 3x6 인 케이스`() = Stream.of(
            Arguments.of(0, 3, 6, listOf(1, 3, 4)),
            Arguments.of(2, 3, 6, listOf(1, 4, 5)),
            Arguments.of(17, 3, 6, listOf(13, 14, 16))
        )
    }
}
