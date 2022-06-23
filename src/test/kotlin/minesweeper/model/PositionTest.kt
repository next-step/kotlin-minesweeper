package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@DisplayName("위치 테스트")
class PositionTest {

    @Test
    fun `생성시 위치 값이 0 미만이면 0으로 생성`() {
        // given, when, then
        assertThat(Position.from(-1).position).isEqualTo(0)
    }

    @Test
    fun `위치값 비교 기능이 정상 동작`() {
        // given
        val position1 = Position.from(1)
        val position2 = Position.from(2)

        // when, then
        assertAll(
            "compare position test",
            { assertThat(position1.isLessThan(position2)).isTrue },
            { assertThat(position2.isGreaterThan(position1)).isTrue }
        )
    }

    @Test
    fun `위치값을 더하는 기능이 정상 동작`() {
        // given
        val position = Position.from(1)

        // when
        val newPosition = position + 2

        // then
        assertThat(newPosition.position).isEqualTo(3)
    }
}
