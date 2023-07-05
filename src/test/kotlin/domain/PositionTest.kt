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

    @Test
    fun `현재 위치를 기준으로 위로 이동한 위치를 반환할 수 있다`() {
        // given
        val tX = 0
        val tY = 1
        val position = Position(tX, tY)

        // when
        val nextPosition = position.move {
            up()
        }

        // then
        assertThat(nextPosition.y).isEqualTo(2)
    }

    @Test
    fun `현재 위치를 기준으로 아래로 이동한 위치를 반환할 수 있다`() {
        // given
        val tX = 0
        val tY = 1
        val position = Position(tX, tY)

        // when
        val nextPosition = position.move {
            down()
        }

        // then
        assertThat(nextPosition.y).isEqualTo(0)
    }

    @Test
    fun `현재 위치를 기준으로 좌측으로 이동한 위치를 반환할 수 있다`() {
        // given
        val tX = 0
        val tY = 1
        val position = Position(tX, tY)

        // when
        val nextPosition = position.move {
            left()
        }

        // then
        assertThat(nextPosition.x).isEqualTo(-1)
    }

    @Test
    fun `현재 위치를 기준으로 우측으로 이동한 위치를 반환할 수 있다`() {
        // given
        val tX = 0
        val tY = 1
        val position = Position(tX, tY)

        // when
        val nextPosition = position.move {
            right()
        }

        // then
        assertThat(nextPosition.x).isEqualTo(1)
    }

    @Test
    fun `현재 위치를 기준으로 여러번 이동한 위치를 반환할 수 있다`() {
        // given
        val tX = 0
        val tY = 1
        val position = Position(tX, tY)

        // when
        val nextPosition = position.move {
            up()
            right()
            up()
            right()
        }

        // then
        assertThat(nextPosition.x).isEqualTo(2)
        assertThat(nextPosition.y).isEqualTo(3)
    }
}
