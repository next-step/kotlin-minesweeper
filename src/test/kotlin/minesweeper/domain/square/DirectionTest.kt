package minesweeper.domain.square

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class DirectionTest {

    @ParameterizedTest
    @EnumSource(value = Direction::class, names = ["UP", "UPPER_LEFT", "UPPER_RIGHT"])
    fun `위쪽 방향인지 확인`(direction: Direction) {
        assertThat(direction.x).isEqualTo(-1)
    }

    @ParameterizedTest
    @EnumSource(value = Direction::class, names = ["DOWN", "DOWN_LEFT", "DOWN_RIGHT"])
    fun `아래쪽 방향인지 확인`(direction: Direction) {
        assertThat(direction.x).isEqualTo(1)
    }

    @ParameterizedTest
    @EnumSource(value = Direction::class, names = ["LEFT", "UPPER_LEFT", "DOWN_LEFT"])
    fun `왼쪽 방향인지 확인`(direction: Direction) {
        assertThat(direction.y).isEqualTo(-1)
    }

    @ParameterizedTest
    @EnumSource(value = Direction::class, names = ["RIGHT", "UPPER_RIGHT", "DOWN_RIGHT"])
    fun `오른쪽 방향인지 확인`(direction: Direction) {
        assertThat(direction.y).isEqualTo(1)
    }
}
