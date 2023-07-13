package minesweeper

import minesweeper.domain.Coordinate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CoordinateTest {
    @Test
    fun `좌표가 특정 범위 안에 있는지 확인할 수 있다`() {
        // given
        val coordinate = Coordinate.of(1, 2)

        // when then
        assertAll({
            assertThat(coordinate.checkWithinBounds(5, 5)).isTrue()
            assertThat(coordinate.checkWithinBounds(1, 1)).isFalse()
        })
    }

    @Test
    fun `원점은 (0, 0)이다`() {
        // given
        val origin = Coordinate.origin()

        // when then
        assertAll({
            assertThat(origin.x).isEqualTo(0)
            assertThat(origin.y).isEqualTo(0)
        })
    }
}
