package minesweeper.domain.directions

import minesweeper.domain.block.Position
import minesweeper.domain.directions.MineSearchDirections.EAST
import minesweeper.domain.directions.MineSearchDirections.NORTH
import minesweeper.domain.directions.MineSearchDirections.NORTH_EAST
import minesweeper.domain.directions.MineSearchDirections.NORTH_WEST
import minesweeper.domain.directions.MineSearchDirections.SOUTH
import minesweeper.domain.directions.MineSearchDirections.SOUTH_EAST
import minesweeper.domain.directions.MineSearchDirections.SOUTH_WEST
import minesweeper.domain.directions.MineSearchDirections.WEST
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("지뢰찾기 방향(MineSearchDirections)")
internal class MineSearchDirectionsTest {

    @ParameterizedTest(name = "X값: {0}, Y값: {1}")
    @CsvSource(value = ["1:1", "2:1", "1:2", "2:2", "10:10"], delimiter = ':')
    fun `여덟 방향에 대한 좌표값을 계산한다`(x: Int, y: Int) {
        val position = Position(x, y)

        assertAll(
            { assertThat(NORTH.nextPosition(position)).isEqualTo(Position(x, y - 1)) },
            { assertThat(NORTH_EAST.nextPosition(position)).isEqualTo(Position(x + 1, y - 1)) },
            { assertThat(EAST.nextPosition(position)).isEqualTo(Position(x + 1, y)) },
            { assertThat(SOUTH_EAST.nextPosition(position)).isEqualTo(Position(x + 1, y + 1)) },
            { assertThat(SOUTH.nextPosition(position)).isEqualTo(Position(x, y + 1)) },
            { assertThat(SOUTH_WEST.nextPosition(position)).isEqualTo(Position(x - 1, y + 1)) },
            { assertThat(WEST.nextPosition(position)).isEqualTo(Position(x - 1, y)) },
            { assertThat(NORTH_WEST.nextPosition(position)).isEqualTo(Position(x - 1, y - 1)) },
        )
    }
}
