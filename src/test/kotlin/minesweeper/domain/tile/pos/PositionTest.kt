package minesweeper.domain.tile.pos

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PositionTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 21])
    fun `Position - 범위(1~20) 초과에 대한 예외처리 테스트`(position: Int) {
        // given, when, then
        Assertions.assertThatThrownBy { Position(position) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("높이와 너비는 1 이상 20 이하의 정수여야 합니다.")
    }

    @ParameterizedTest
    @ValueSource(strings = ["", " ", "mine"])
    fun `Position - 숫자 이외의 값 입력에 대한 예외처리 테스트`(input: String) {
        // given, when, then
        Assertions.assertThatThrownBy { Position(input) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("숫자 이외의 값은 입력할 수 없습니다.")
    }
}
