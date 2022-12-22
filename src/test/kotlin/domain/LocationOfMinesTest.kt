package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class LocationOfMinesTest {

    /**
     * |   C * C C
     * |   C * * C
     * |   C C * C
     */
    @Test
    fun `주변에 있는 마인 갯수를 반환한다`() {
        // given
        val mines = LocationOfMines(
            listOf(
                Coordinate(0, 1),
                Coordinate(1, 1),
                Coordinate(1, 2),
                Coordinate(2, 2),
            )
        )

        // when, then
        assertAll(
            { assertThat(mines.countByNearMines(Coordinate(0, 0))).isEqualTo(2) },
            { assertThat(mines.countByNearMines(Coordinate(0, 2))).isEqualTo(3) },
            { assertThat(mines.countByNearMines(Coordinate(2, 1))).isEqualTo(3) },
            { assertThat(mines.countByNearMines(Coordinate(2, 3))).isEqualTo(2) }
        )
    }
}
