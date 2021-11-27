package minesweeper.domain.position

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CompassDirectionsTest {

    @Test
    fun `한 포지션에 대하여 8개의 방향이 맞는지 테스트`() {
        // given
        val compassDirections = CompassDirections.of(
            Position.of(5, 6)
        )

        // then
        assertAll(
            {
                assertThat(compassDirections[0]).isEqualTo(Position.of(5, 7))
                assertThat(compassDirections[1]).isEqualTo(Position.of(4, 7))
                assertThat(compassDirections[2]).isEqualTo(Position.of(6, 7))
                assertThat(compassDirections[3]).isEqualTo(Position.of(4, 6))
                assertThat(compassDirections[4]).isEqualTo(Position.of(6, 6))
                assertThat(compassDirections[5]).isEqualTo(Position.of(5, 5))
                assertThat(compassDirections[6]).isEqualTo(Position.of(4, 5))
                assertThat(compassDirections[7]).isEqualTo(Position.of(6, 5))
            }
        )
    }
}
