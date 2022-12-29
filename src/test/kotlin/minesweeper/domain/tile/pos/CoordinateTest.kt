package minesweeper.domain.tile.pos

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CoordinateTest {
    @Test
    fun `Coordinate - 좌표값 같음`() {
        // given
        val coordinate1 = Coordinate.of(1, 1)
        val coordinate2 = Coordinate.of(1, 1)

        // when
        val actual = coordinate1 == coordinate2

        // then
        assertThat(actual).isTrue
    }

    @Test
    fun `Coordinate - 좌표값 다름`() {
        // given
        val coordinate1 = Coordinate.of(1, 1)
        val coordinate2 = Coordinate.of(1, 2)

        // when
        val actual = coordinate1 == coordinate2

        // then
        assertThat(actual).isFalse
    }
}
