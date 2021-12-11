package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("방향(Directions)")
internal class DirectionsTest {

    @ParameterizedTest(name = "X값: {0}, Y값: {1}")
    @CsvSource(value = ["0:0", "1:0", "0:1", "1:1", "10:10"], delimiter = ':')
    fun `여덟 방향에 대한 좌표값을 계산한다`(x: Int, y: Int) {

        assertAll(
            { assertThat(Directions.NORTH.nextCoordinate(x, y)).isEqualTo(x to y - 1) },
            { assertThat(Directions.NORTH_EAST.nextCoordinate(x, y)).isEqualTo(x + 1 to y - 1) },
            { assertThat(Directions.EAST.nextCoordinate(x, y)).isEqualTo(x + 1 to y) },
            { assertThat(Directions.SOUTH_EAST.nextCoordinate(x, y)).isEqualTo(x + 1 to y + 1) },
            { assertThat(Directions.SOUTH.nextCoordinate(x, y)).isEqualTo(x to y + 1) },
            { assertThat(Directions.SOUTH_WEST.nextCoordinate(x, y)).isEqualTo(x - 1 to y + 1) },
            { assertThat(Directions.WEST.nextCoordinate(x, y)).isEqualTo(x - 1 to y) },
            { assertThat(Directions.NORTH_WEST.nextCoordinate(x, y)).isEqualTo(x - 1 to y - 1) },
        )
    }
}
