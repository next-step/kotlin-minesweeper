package minesweeper.domain.board

import minesweeper.domain.block.MineSearchDirections
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("오픈 방향(OpenDirectionsTest)")
internal class OpenDirectionsTest {

    @ParameterizedTest(name = "X값: {0}, Y값: {1}")
    @CsvSource(value = ["0:0", "1:0", "0:1", "1:1", "10:10"], delimiter = ':')
    fun `여덟 방향에 대한 좌표값을 계산한다`(x: Int, y: Int) {

        org.junit.jupiter.api.assertAll(
            { assertThat(OpenDirections.NORTH.nextCoordinate(x, y)).isEqualTo(x to y - 1) },
            { assertThat(MineSearchDirections.EAST.nextCoordinate(x, y)).isEqualTo(x + 1 to y) },
            { assertThat(MineSearchDirections.SOUTH.nextCoordinate(x, y)).isEqualTo(x to y + 1) },
            { assertThat(MineSearchDirections.WEST.nextCoordinate(x, y)).isEqualTo(x - 1 to y) },
        )
    }
}
