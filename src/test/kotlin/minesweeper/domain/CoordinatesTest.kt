package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CoordinatesTest {
    @Test
    fun make_coordinates() {
        val width = 10
        val length = 10

        val coordinates = Coordinates(width, length)

        assertThat(coordinates.coordinates).hasSize(100)
    }

    @Test
    fun make_mine_coordinates() {
        val width = 10
        val length = 10
        val mines = 10
        val coordinates = Coordinates(width, length)

        val mineCoordinates = coordinates.makeMineCoordinates(mines)

        assertThat(mineCoordinates).hasSize(10)
    }
}
