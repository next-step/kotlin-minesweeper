package minesweeper.domain.directions

import minesweeper.domain.block.Position
import minesweeper.domain.directions.MineSearchDirections.SOUTH
import minesweeper.domain.directions.MineSearchDirections.WEST
import minesweeper.domain.directions.OpenDirections.EAST
import minesweeper.domain.directions.OpenDirections.NORTH
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("오픈 방향(OpenDirectionsTest)")
internal class OpenDirectionsTest {

    @ParameterizedTest(name = "X값: {0}, Y값: {1}")
    @CsvSource(value = ["1:1", "2:1", "1:2", "2:2", "10:10"], delimiter = ':')
    fun `여덟 방향에 대한 좌표값을 계산한다`(x: Int, y: Int) {
        val position = Position(x, y)

        assertAll(
            { assertThat(NORTH.nextPosition(position)).isEqualTo(Position(x, y - 1)) },
            { assertThat(EAST.nextPosition(position)).isEqualTo(Position(x + 1, y)) },
            { assertThat(SOUTH.nextPosition(position)).isEqualTo(Position(x, y + 1)) },
            { assertThat(WEST.nextPosition(position)).isEqualTo(Position(x - 1, y)) },
        )
    }
}
