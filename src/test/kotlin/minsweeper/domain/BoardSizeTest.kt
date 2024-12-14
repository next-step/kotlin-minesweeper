package minsweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BoardSizeTest {

    @ParameterizedTest
    @ValueSource(ints = [0, -1])
    fun `너비에 값이 0이하이면 에러를 던져야 한다`(width: Int) {
        // given

        // when
        val result = assertThrows(IllegalArgumentException::class.java) {
            BoardSize(10, width)
        }

        // then
        assertThat(result.message).isEqualTo("너비는 0보다 커야합니다")
    }

    @ParameterizedTest
    @ValueSource(ints = [0, -1])
    fun `높이에 값이 0이하이면 에러를 던져야 한다`(height: Int) {
        // given

        // when
        val result = assertThrows(IllegalArgumentException::class.java) {
            BoardSize(height, 10)
        }

        // then
        assertThat(result.message).isEqualTo("높이는 0보다 커야합니다")
    }

}
