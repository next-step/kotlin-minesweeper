package minesweeper.domain.area

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class WidthTest {

    @ParameterizedTest
    @ValueSource(ints = [1, 10, 100])
    fun `0이상 수를 입력하여 너비를 생성할 수 있다`(value: Int) {
        assertThat(Width(value)).isNotNull
    }

    @ParameterizedTest
    @ValueSource(ints = [0, -1, -10])
    fun `0보다 작은 수를 입력하면 예외를 던진다`(value: Int) {
        Assertions.assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy { Width(value) }.withMessage("최소 0보다 커야합니다")
    }
}
