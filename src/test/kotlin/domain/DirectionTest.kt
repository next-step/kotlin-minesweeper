package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DirectionTest {

    @Test
    fun `주변 8개의 좌표를 찾는다`() {
        assertThat(Direction.findSurroundings(Location(1, 1))).contains(
            Location(0, 0),
            Location(0, 1),
            Location(1, 0),
            Location(2, 0),
            Location(0, 2),
            Location(2, 2),
            Location(2, 1),
            Location(1, 2)
        )
    }
}
