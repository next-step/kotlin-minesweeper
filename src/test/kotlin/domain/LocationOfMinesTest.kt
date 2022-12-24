package domain

import domain.coord.AbstractCoordinate
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
                AbstractCoordinate(0, 1),
                AbstractCoordinate(1, 1),
                AbstractCoordinate(1, 2),
                AbstractCoordinate(2, 2),
            )
        )

        // when, then
        assertAll(
            { assertThat(mines.countByNearMines(AbstractCoordinate(0, 0))).isEqualTo(2) },
            { assertThat(mines.countByNearMines(AbstractCoordinate(0, 2))).isEqualTo(3) },
            { assertThat(mines.countByNearMines(AbstractCoordinate(2, 1))).isEqualTo(3) },
            { assertThat(mines.countByNearMines(AbstractCoordinate(2, 3))).isEqualTo(2) }
        )
    }
}
