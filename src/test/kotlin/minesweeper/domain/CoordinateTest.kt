package minesweeper.domain

import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@Suppress("NonAsciiCharacters")
class CoordinateTest {
    @Test
    fun `y축 좌표는 0 이상이어야 한다`() {
        assertThrows<IllegalArgumentException> { Coordinate(y = -1, x = 1) }
    }

    @Test
    fun `y축 좌표는 1_000_000 이하이어야 한다`() {
        assertThrows<IllegalArgumentException> { Coordinate(y = 1_000_001, x = 1) }
    }

    @Test
    fun `x축 좌표는 0 이상이어야 한다`() {
        assertThrows<IllegalArgumentException> { Coordinate(y = 1, x = -1) }
    }

    @Test
    fun `x축 좌표는 1_000_000 이하이어야 한다`() {
        assertThrows<IllegalArgumentException> { Coordinate(y = 1, x = 1_000_001) }
    }

    @Test
    fun `내부 좌표의 주변에는 8개의 좌표를 구한다`() {
        val coordinate = Coordinate(y = 1, x = 1)

        val neighbors = coordinate.neighbors

        neighbors shouldContainExactlyInAnyOrder
            listOf(
                Coordinate(y = 0, x = 0),
                Coordinate(y = 0, x = 1),
                Coordinate(y = 0, x = 2),
                Coordinate(y = 1, x = 0),
                Coordinate(y = 1, x = 2),
                Coordinate(y = 2, x = 0),
                Coordinate(y = 2, x = 1),
                Coordinate(y = 2, x = 2),
            )
    }

    @ParameterizedTest
    @MethodSource
    fun `코너인 경우 주변 좌표가 3개 있다`(
        coordinate: Coordinate,
        expected: List<Coordinate>,
    ) {
        val neighbors = coordinate.neighbors
        neighbors shouldContainExactlyInAnyOrder expected
    }

    @ParameterizedTest
    @MethodSource
    fun `외곽 변에 있는 칸은 주변 좌표가 5개 있다`(
        coordinate: Coordinate,
        expected: List<Coordinate>,
    ) {
        val neighbors = coordinate.neighbors
        neighbors shouldContainExactlyInAnyOrder expected
    }

    companion object {
        @JvmStatic
        fun `코너인 경우 주변 좌표가 3개 있다`() =
            listOf(
                Arguments.of(
                    Coordinate(y = 0, x = 0),
                    listOf(
                        Coordinate(y = 0, x = 1),
                        Coordinate(y = 1, x = 0),
                        Coordinate(y = 1, x = 1),
                    ),
                ),
                Arguments.of(
                    Coordinate(y = Coordinate.MAX_VALUE, x = 0),
                    listOf(
                        Coordinate(y = Coordinate.MAX_VALUE - 1, x = 0),
                        Coordinate(y = Coordinate.MAX_VALUE - 1, x = 1),
                        Coordinate(y = Coordinate.MAX_VALUE, x = 1),
                    ),
                ),
                Arguments.of(
                    Coordinate(y = 0, x = Coordinate.MAX_VALUE),
                    listOf(
                        Coordinate(y = 0, x = Coordinate.MAX_VALUE - 1),
                        Coordinate(y = 1, x = Coordinate.MAX_VALUE - 1),
                        Coordinate(y = 1, x = Coordinate.MAX_VALUE),
                    ),
                ),
                Arguments.of(
                    Coordinate(y = Coordinate.MAX_VALUE, x = Coordinate.MAX_VALUE),
                    listOf(
                        Coordinate(y = Coordinate.MAX_VALUE - 1, x = Coordinate.MAX_VALUE),
                        Coordinate(y = Coordinate.MAX_VALUE - 1, x = Coordinate.MAX_VALUE - 1),
                        Coordinate(y = Coordinate.MAX_VALUE, x = Coordinate.MAX_VALUE - 1),
                    ),
                ),
            )

        @JvmStatic
        fun `외곽 변에 있는 칸은 주변 좌표가 5개 있다`() =
            listOf(
                Arguments.of(
                    Coordinate(y = 0, x = 1),
                    listOf(
                        Coordinate(y = 0, x = 0),
                        Coordinate(y = 1, x = 0),
                        Coordinate(y = 1, x = 1),
                        Coordinate(y = 1, x = 2),
                        Coordinate(y = 0, x = 2),
                    ),
                ),
                Arguments.of(
                    Coordinate(y = 1, x = 0),
                    listOf(
                        Coordinate(y = 0, x = 0),
                        Coordinate(y = 0, x = 1),
                        Coordinate(y = 1, x = 1),
                        Coordinate(y = 2, x = 1),
                        Coordinate(y = 2, x = 0),
                    ),
                ),
                Arguments.of(
                    Coordinate(y = Coordinate.MAX_VALUE, x = 1),
                    listOf(
                        Coordinate(y = Coordinate.MAX_VALUE, x = 0),
                        Coordinate(y = Coordinate.MAX_VALUE - 1, x = 0),
                        Coordinate(y = Coordinate.MAX_VALUE - 1, x = 1),
                        Coordinate(y = Coordinate.MAX_VALUE - 1, x = 2),
                        Coordinate(y = Coordinate.MAX_VALUE, x = 2),
                    ),
                ),
                Arguments.of(
                    Coordinate(y = 1, x = Coordinate.MAX_VALUE),
                    listOf(
                        Coordinate(y = 0, x = Coordinate.MAX_VALUE),
                        Coordinate(y = 0, x = Coordinate.MAX_VALUE - 1),
                        Coordinate(y = 1, x = Coordinate.MAX_VALUE - 1),
                        Coordinate(y = 2, x = Coordinate.MAX_VALUE - 1),
                        Coordinate(y = 2, x = Coordinate.MAX_VALUE),
                    ),
                ),
            )
    }
}
