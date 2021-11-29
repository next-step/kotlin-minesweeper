package minesweeper.domain.area

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class HeightTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 10, 100])
    fun `0이상 수를 입력하여 높이를 생성할 수 있다`(value: Int) {
        assertThat(Height(value)).isNotNull
    }

    @ParameterizedTest
    @ValueSource(ints = [0, -1, -10])
    fun `0보다 작은 수를 입력하면 예외를 던진다`(value: Int) {
        assertThrows<IllegalArgumentException> { (Height(value)) }
    }
}
