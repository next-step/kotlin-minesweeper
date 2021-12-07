package minesweeper.domain

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("방향(Direction)")
internal class DirectionTest {

    @ParameterizedTest(name = "X값: {0}, Y값: {1}")
    @CsvSource()
    fun `여덟 방향에 대한 좌표값을 계산한다`(x: Int, y: Int) {

        assertAll(
            { Direction.NORTH.nextPosition(x, y).isEqualTo(x to y - 1) },
            { Direction.NORTH_EAST.nextPosition(x, y).isEqualTo(x + 1 to y) },
            { Direction.EAST.nextPosition(x, y).isEqualTo(x + 1 to y) },
            { Direction.SOUTH_EAST.nextPosition(x, y).isEqualTo(x + 1, y) },
            { Direction.SOUTH.nextPosition(x, y).isEqualTo(x + 1 to y) },
            { Direction.SOUTH_WEST.nextPosition(x, y).isEqualTo(x + 1 to y) },
            { Direction.WEST.nextPosition(x, y).isEqualTo(x + 1 to y) },
            { Direction.NORTH_WEST.nextPosition(x, y).isEqualTo(x + 1 to y) },
        )
    }
}
