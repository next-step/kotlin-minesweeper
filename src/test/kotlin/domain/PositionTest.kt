package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Created by Jaesungchi on 2022.06.28..
 */
class PositionTest {
    @Test
    fun `좌표에 0미만의 값이 들어가면 IllegalArgumentException을 던진다`() {
        assertThrows<IllegalArgumentException> {
            Position(0, -1)
        }
    }

    @Test
    fun `만들수 없는 포지션을 만들면 null을 반환한다`() {
        assertThat(Position.makePositionOrNull(-1, -1, 1, 1)).isNull()
    }
}
