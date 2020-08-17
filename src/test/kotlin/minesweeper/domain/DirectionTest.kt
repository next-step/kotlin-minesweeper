package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DirectionTest {
    @Test
    fun has_8_direction() {
        assertThat(Direction.values()).hasSize(8)
    }

    @Test
    fun getCoordinate() {
        val coordinate = Coordinate(1, 1)

        val north = Direction.NORTH.getCoordinate(coordinate)

        assertThat(north.x).isEqualTo(1)
        assertThat(north.y).isEqualTo(2)
    }
}
