package game.minesweeper.domain.map

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("좌표")
internal class CoordinateTest {

    @Test
    fun `같은 좌표 비교`() {
        val coordinate = Coordinate(1, 1)
        val other = Coordinate(1, 1)
        assertThat(coordinate).isEqualTo(other)
    }

    @Test
    fun `다른 좌표 비교`() {
        val coordinate = Coordinate(1, 1)
        val other = Coordinate(1, 2)
        assertThat(coordinate).isNotEqualTo(other)
    }

    @Test
    fun `올바르지 않은 좌표는 예외`() {
        assertThrows<IllegalArgumentException> { Coordinate(0, 0) }
    }
}
