package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

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
}
