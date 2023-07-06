package minesweeper

import minesweeper.domain.Coordinate
import minesweeper.domain.Coordinates
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CoordinatesTest {
    @Test
    fun `여러 좌표들이 모두 특정 범위 안에 있는지 확인할 수 있다`() {
        // given
        val coordinate1 = Coordinate.of(1, 2)
        val coordinate2 = Coordinate.of(2, 2)
        val coordinate3 = Coordinate.of(4, 4)

        val coordinates = Coordinates(setOf(coordinate1, coordinate2, coordinate3))

        // when then
        assertAll({
            Assertions.assertThat(coordinates.checkWithinBounds(2, 3)).isFalse()
            Assertions.assertThat(coordinates.checkWithinBounds(10, 10)).isTrue()
        })
    }
}
