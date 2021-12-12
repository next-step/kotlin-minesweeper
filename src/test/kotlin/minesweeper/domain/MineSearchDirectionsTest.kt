package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("지뢰찾기 방향(MineSearchDirections)")
internal class MineSearchDirectionsTest {

    @ParameterizedTest(name = "X값: {0}, Y값: {1}")
    @CsvSource(value = ["0:0", "1:0", "0:1", "1:1", "10:10"], delimiter = ':')
    fun `여덟 방향에 대한 좌표값을 계산한다`(x: Int, y: Int) {

        assertAll(
            { assertThat(MineSearchDirections.NORTH.nextCoordinate(x, y)).isEqualTo(x to y - 1) },
            { assertThat(MineSearchDirections.NORTH_EAST.nextCoordinate(x, y)).isEqualTo(x + 1 to y - 1) },
            { assertThat(MineSearchDirections.EAST.nextCoordinate(x, y)).isEqualTo(x + 1 to y) },
            { assertThat(MineSearchDirections.SOUTH_EAST.nextCoordinate(x, y)).isEqualTo(x + 1 to y + 1) },
            { assertThat(MineSearchDirections.SOUTH.nextCoordinate(x, y)).isEqualTo(x to y + 1) },
            { assertThat(MineSearchDirections.SOUTH_WEST.nextCoordinate(x, y)).isEqualTo(x - 1 to y + 1) },
            { assertThat(MineSearchDirections.WEST.nextCoordinate(x, y)).isEqualTo(x - 1 to y) },
            { assertThat(MineSearchDirections.NORTH_WEST.nextCoordinate(x, y)).isEqualTo(x - 1 to y - 1) },
        )
    }
}
