package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CoordinateTest {

    @Test
    fun `자신을 제외한 주변 8방향의 좌표를 찾는다`() {
        val coordinate = Coordinate(1, 0)
        assertThat(coordinate.findNotifyRange(3, 3)).contains(
            Coordinate(0, 0), Coordinate(0, 1), Coordinate(1, 1),
            Coordinate(2, 0), Coordinate(2, 1)
        )
    }

    @Test
    fun `지정한 방향의 좌표를 얻는다`() {
        val coordinate = Coordinate(1, 1)
        assertThat(coordinate.applyDirection(Direction.TOP, 3, 4)).isEqualTo(Coordinate(1, 2))
        assertThat(coordinate.applyDirection(Direction.RIGHT, 2, 2)).isEqualTo(Coordinate(1, 1))
    }
}
