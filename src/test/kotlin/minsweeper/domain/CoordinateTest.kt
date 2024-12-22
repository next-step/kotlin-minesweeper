package minsweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CoordinateTest {

    @Test
    fun `좌표가 1보다 작으면 에러를 던져야 한다`() {
        // given

        // when
        val result = assertThrows<IllegalArgumentException> { Coordinate(0, 0) }

        // then
        assertThat(result.message).isEqualTo("좌표는 0보다 커야 합니다")
    }

}