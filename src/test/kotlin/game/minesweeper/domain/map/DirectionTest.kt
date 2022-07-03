package game.minesweeper.domain.map

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("좌표의 경계 8가지 방향")
internal class DirectionTest {

    @Test
    fun `→ 방향 좌표 얻기`() {
        val coordinate = Coordinate(5, 5)
        val there = Direction.East.from(coordinate)
        assertThat(there).isEqualTo(Coordinate(5, 6))
    }

    @Test
    fun `↘ 방향 좌표 얻기`() {
        val coordinate = Coordinate(5, 5)
        val there = Direction.Southeast.from(coordinate)
        assertThat(there).isEqualTo(Coordinate(6, 6))
    }

    @Test
    fun `↓ 방향 좌표 얻기`() {
        val coordinate = Coordinate(5, 5)
        val there = Direction.South.from(coordinate)
        assertThat(there).isEqualTo(Coordinate(6, 5))
    }

    @Test
    fun `↙ 방향 좌표 얻기`() {
        val coordinate = Coordinate(5, 5)
        val there = Direction.Southwest.from(coordinate)
        assertThat(there).isEqualTo(Coordinate(6, 4))
    }

    @Test
    fun `← 방향 좌표 얻기`() {
        val coordinate = Coordinate(5, 5)
        val there = Direction.West.from(coordinate)
        assertThat(there).isEqualTo(Coordinate(5, 4))
    }

    @Test
    fun `↖ 방향 좌표 얻기`() {
        val coordinate = Coordinate(5, 5)
        val there = Direction.Northwest.from(coordinate)
        assertThat(there).isEqualTo(Coordinate(4, 4))
    }

    @Test
    fun `↑ 방향 좌표 얻기`() {
        val coordinate = Coordinate(5, 5)
        val there = Direction.North.from(coordinate)
        assertThat(there).isEqualTo(Coordinate(4, 5))
    }

    @Test
    fun `↗ 방향 좌표 얻기`() {
        val coordinate = Coordinate(5, 5)
        val there = Direction.Northeast.from(coordinate)
        assertThat(there).isEqualTo(Coordinate(4, 6))
    }

    @Test
    fun `경계선 ↖ 방향 좌표 얻기`() {
        val coordinate = Coordinate(1, 1)
        val there = Direction.Northwest.from(coordinate)
        assertThat(there).isNull()
    }
}
