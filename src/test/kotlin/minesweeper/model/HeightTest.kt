package minesweeper.model

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class HeightTest {

    @ParameterizedTest
    @ValueSource(ints = [-1, 0])
    fun `높이는 1 이상이어야 한다`(height: Int) {
        assertThatIllegalArgumentException().isThrownBy { Height(height) }
    }
}
