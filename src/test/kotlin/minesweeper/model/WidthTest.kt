package minesweeper.model

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class WidthTest {

    @ParameterizedTest
    @ValueSource(ints = [-1, 0])
    fun `너비는 1 이상이어야 한다`(width: Int) {
        assertThatIllegalArgumentException().isThrownBy { Width(width) }
    }
}
