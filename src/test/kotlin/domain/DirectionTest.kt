package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DirectionTest {
    @DisplayName("지뢰를 기준으로 주변 8개 사각형 포지션을 return한다.")
    @Test
    fun apply() {
        val position = Position(5, 5)
        val mineCountPosition = Direction.values().map {
            it.apply(position.getX(), position.getX())
        }
        val result = listOf(
            Position(4, 4),
            Position(4, 5),
            Position(4, 6),
            Position(5, 4),
            Position(5, 6),
            Position(6, 4),
            Position(6, 5),
            Position(6, 6)
        )
        assertThat(mineCountPosition).isEqualTo(result)
    }
}
