package minesweeper

import minesweeper.domain.Coordinates
import minesweeper.domain.RandomCoordinatesGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CoordinatesGeneratorTest {
    @Test
    fun `좌표들을 생성할 수 있다`() {
        val height = 3
        val width = 3
        val numberOfMines = 2
        val randomCoordinatesGenerator = RandomCoordinatesGenerator(height, width)
        assertThat(randomCoordinatesGenerator.create(numberOfMines)).isInstanceOf(Coordinates::class.java)
    }
}
