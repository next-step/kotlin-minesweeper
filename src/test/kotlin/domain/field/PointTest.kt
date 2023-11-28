package domain.field

import domain.field.Point.Companion.inputListToPoint
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class PointTest {

    @ParameterizedTest
    @CsvSource(value = ["1,1,2,2", "1,2,2,3", "2,1,3,2", "2,2,3,3"])
    fun `Point 더하기`(y: Int, x: Int, otherY: Int, otherX: Int) {
        val point = Point(y, x)
        val otherPoint = Point(otherY, otherX)
        val expected = Point(y + otherY, x + otherX)
        val actual = point + otherPoint

        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("inputListExample")
    fun `input list to point`(list: List<Int>, expected: Point) {
        assertThat(list.inputListToPoint()).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun inputListExample(): Stream<Arguments> = Stream.of(
            Arguments.of(listOf(1, 1), Point(0, 0)),
            Arguments.of(listOf(1, 2), Point(1, 0)),
            Arguments.of(listOf(2, 1), Point(0, 1)),
            Arguments.of(listOf(2, 2), Point(1, 1))
        )
    }
}
