package minsweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CoordinateTest {

    @Test
    fun `좌표가 1보다 작으면 에러를 던져야 한다`() {
        // given

        // when
        val result = assertThrows<IllegalArgumentException> { Coordinate.of(0, 0) }

        // then
        assertThat(result.message).isEqualTo("좌표는 0보다 커야 합니다")
    }

    @ParameterizedTest
    @ValueSource(strings = ["1, 2, 3", "1", "str, rrr", "str"])
    fun `올바른 입력값으로 좌표를 생성하지 않으면 아니며 에러를 던져야 한다`(input: String) {
        // given

        // when
        val result = assertThrows<IllegalArgumentException> { Coordinate.of(input) }

        // then
        assertThat(result.message).isEqualTo("입력값이 올바르지 않습니다")
    }

    @Test
    fun `올바르게 인풋을 입력하면 좌표를 생성한다`() {
        // given
        val input = "1, 2"

        // when
        val coordinate = Coordinate.of(input)

        // then
        assertThat(coordinate.row).isEqualTo(1)
        assertThat(coordinate.column).isEqualTo(2)
    }

}