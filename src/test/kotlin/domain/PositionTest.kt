package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PositionTest {

    @Test
    fun `좌표를 통해 위치를 생성할 수 있다`() {
        // given
        val tX = 0
        val tY = 1

        // when
        val position = Position(tX, tY)

        // then
        with(position) {
            assertThat(x).isEqualTo(tX)
            assertThat(y).isEqualTo(tY)
        }
    }

    @Test
    fun `서로 같은 좌표를 구분할 수 있다`() {
        // given
        val tX = 0
        val tY = 1

        // when
        val position1 = Position(tX, tY)
        val position2 = Position(tX, tY)

        // then
        assertThat(position1 == position2).isEqualTo(true)
    }

    @Test
    fun `서로 다른 좌표를 구분할 수 있다`() {
        // given
        val tX = 0
        val tY = 1

        // when
        val position1 = Position(tX, tY)
        val position2 = Position(100, 200)

        // then
        assertThat(position1 == position2).isEqualTo(false)
    }
}
