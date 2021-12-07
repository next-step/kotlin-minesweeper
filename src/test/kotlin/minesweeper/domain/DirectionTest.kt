package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("방향(Direction)")
internal class DirectionTest {

    @ParameterizedTest(name = "X값: {0}, Y값: {1}")
    @CsvSource(value = ["0:0", "1:0", "0:1", "1:1", "10:10"], delimiter = ':')
    fun `여덟 방향에 대한 좌표값을 계산한다`(x: Int, y: Int) {

        assertAll(
            { assertThat(Direction.NORTH.nextPosition(x, y)).isEqualTo(x to y - 1) },
            { assertThat(Direction.NORTH_EAST.nextPosition(x, y)).isEqualTo(x + 1 to y - 1) },
            { assertThat(Direction.EAST.nextPosition(x, y)).isEqualTo(x + 1 to y) },
            { assertThat(Direction.SOUTH_EAST.nextPosition(x, y)).isEqualTo(x + 1 to y + 1) },
            { assertThat(Direction.SOUTH.nextPosition(x, y)).isEqualTo(x to y + 1) },
            { assertThat(Direction.SOUTH_WEST.nextPosition(x, y)).isEqualTo(x - 1 to y + 1) },
            { assertThat(Direction.WEST.nextPosition(x, y)).isEqualTo(x - 1 to y) },
            { assertThat(Direction.NORTH_WEST.nextPosition(x, y)).isEqualTo(x - 1 to y - 1) },
        )
    }
}
