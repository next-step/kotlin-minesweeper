package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MineCountTest {

    @ParameterizedTest
    @ValueSource(ints = [-1, -100, -150])
    fun `지뢰 개수는 0보다 작을 수 없다`(count: Int) {
        assertThat(MineCount.valueOf(count)).isEqualTo(MineCount.ZERO)
    }
}
