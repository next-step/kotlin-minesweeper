package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class CoordinateTest {

    @ParameterizedTest
    @CsvSource(
        "0, 0",
        "1, -1",
        "1, 1",
        "-1, 0"
    )
    fun `좌표 생성한다`(x: Int, y: Int) {
        val result = Coordinate(x = x, y = y)
        assertThat(result).isNotNull()
    }

    @ParameterizedTest
    @MethodSource("provideSurroundingCoordinates")
    fun `특정 좌표의 둘러싸는 좌표들을 반환한다`(coordinate: Coordinate, dummyMaxValue: Int, expected: Set<Coordinate>) {
        val result = coordinate.getSurroundingCoordinates(maxX = dummyMaxValue, maxY = dummyMaxValue)
        assertThat(result).containsExactlyInAnyOrder(*expected.toTypedArray())
    }

    @ParameterizedTest
    @MethodSource("provideRangedSurroundingCoordinates")
    fun `특정 좌표의 둘러싸는 좌표들 중 지정한 범위 이내의 좌표만 반환할 수 있다`(
        coordinate: Coordinate,
        maxX: Int,
        maxY: Int,
        expected: Set<Coordinate>
    ) {
        val result = coordinate.getSurroundingCoordinates(maxX = maxX, maxY = maxY)
        assertThat(result).containsExactlyInAnyOrder(*expected.toTypedArray())
    }

    @ParameterizedTest
    @MethodSource("provideFourWayCoordinates")
    fun `특정 좌표의 동서남들 좌표들을 반환한다`(coordinate: Coordinate, dummyMaxValue: Int, expected: Set<Coordinate>) {
        val result = coordinate.getFourWayCoordinates(maxX = dummyMaxValue, maxY = dummyMaxValue)
        assertThat(result).containsExactlyInAnyOrder(*expected.toTypedArray())
    }

    @ParameterizedTest
    @MethodSource("provideRangedFourWayCoordinates")
    fun `특정 좌표의 동서남북 좌표들 중 지정한 범위 이내의 좌표만 반환할 수 있다`(
        coordinate: Coordinate,
        maxX: Int,
        maxY: Int,
        expected: Set<Coordinate>
    ) {
        val result = coordinate.getFourWayCoordinates(maxX = maxX, maxY = maxY)
        assertThat(result).containsExactlyInAnyOrder(*expected.toTypedArray())
    }

    companion object {
        @JvmStatic
        private fun provideSurroundingCoordinates(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    Coordinate(0, 1),
                    10,
                    setOf(
                        Coordinate(1, 1),
                        Coordinate(1, 2)
                    )
                ),

                Arguments.of(
                    Coordinate(2, 2),
                    10,
                    setOf(
                        Coordinate(1, 1),
                        Coordinate(1, 2),
                        Coordinate(1, 3),
                        Coordinate(2, 1),
                        Coordinate(2, 3),
                        Coordinate(3, 1),
                        Coordinate(3, 2),
                        Coordinate(3, 3)
                    )
                )
            )
        }

        @JvmStatic
        private fun provideRangedSurroundingCoordinates(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    Coordinate(2, 2),
                    2,
                    2,
                    setOf(
                        Coordinate(1, 1),
                        Coordinate(1, 2),
                        Coordinate(2, 1)
                    )
                ),
                Arguments.of(
                    Coordinate(1, 1),
                    1,
                    1,
                    emptySet<Coordinate>()
                ),
                Arguments.of(
                    Coordinate(2, 2),
                    3,
                    2,
                    setOf(
                        Coordinate(1, 1),
                        Coordinate(2, 1),
                        Coordinate(3, 1),
                        Coordinate(1, 2),
                        Coordinate(3, 2)
                    )
                )
            )
        }

        @JvmStatic
        private fun provideFourWayCoordinates(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    Coordinate(0, 1),
                    10,
                    setOf(
                        Coordinate(1, 1)
                    )
                ),

                Arguments.of(
                    Coordinate(2, 2),
                    10,
                    setOf(
                        Coordinate(1, 2),
                        Coordinate(2, 1),
                        Coordinate(2, 3),
                        Coordinate(3, 2)
                    )
                )
            )
        }

        @JvmStatic
        private fun provideRangedFourWayCoordinates(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    Coordinate(2, 2),
                    2,
                    2,
                    setOf(
                        Coordinate(1, 2),
                        Coordinate(2, 1)
                    )
                ),
                Arguments.of(
                    Coordinate(1, 1),
                    1,
                    1,
                    emptySet<Coordinate>()
                ),
                Arguments.of(
                    Coordinate(2, 2),
                    3,
                    2,
                    setOf(
                        Coordinate(2, 1),
                        Coordinate(1, 2),
                        Coordinate(3, 2)
                    )
                )
            )
        }
    }
}
