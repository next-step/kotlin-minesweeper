package minesweeper.domain.land.state

import minesweeper.domain.land.state.Size.Companion.MAXIMUM_SIZE
import minesweeper.domain.land.state.Size.Companion.MINIMUM_SIZE
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class SizeTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 21])
    fun `Size - 범위(1~20) 초과에 대한 예외처리 테스트`(size: Int) {
        // given, when, then
        Assertions.assertThatThrownBy { Size(size) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("높이와 너비는 $MINIMUM_SIZE 이상 $MAXIMUM_SIZE 이하의 정수여야 합니다.")
    }

    @ParameterizedTest
    @ValueSource(strings = ["", " ", "mine"])
    fun `Size - 숫자 이외의 값 입력에 대한 예외처리 테스트`(input: String) {
        // given, when, then
        Assertions.assertThatThrownBy { Size(input) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("숫자 이외의 값은 입력할 수 없습니다.")
    }
}
