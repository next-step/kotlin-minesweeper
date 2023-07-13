package minesweeper

import minesweeper.domain.Coordinates
import minesweeper.domain.RandomCoordinatesGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CoordinatesGeneratorTest {
    @Test
    fun `좌표들을 생성할 수 있다`() {
        // given
        val height = 3
        val width = 3
        val numberOfMines = 2

        // when
        val randomCoordinates = RandomCoordinatesGenerator(height, width).create(numberOfMines)

        // then
        assertThat(randomCoordinates).isInstanceOf(Coordinates::class.java)
    }
}
