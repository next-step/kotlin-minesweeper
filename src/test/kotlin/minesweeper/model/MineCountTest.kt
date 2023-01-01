package minesweeper.model

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class MineCountTest {

    @ParameterizedTest
    @ValueSource(ints = [-1, 0])
    fun `지뢰 개수는 1 이상이어야 한다`(mineCount: Int) {
        assertThatIllegalArgumentException().isThrownBy { MineCount(mineCount) }
    }
}
