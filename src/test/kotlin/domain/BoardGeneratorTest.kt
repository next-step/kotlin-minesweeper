package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BoardGeneratorTest {

    @ParameterizedTest
    @ValueSource(ints = [101, -1])
    fun `지뢰의 개수는 1 이상 (높이 * 너비) 이하여야 한다`(mineCount: Int) {
        assertThat(BoardGenerator.getOrNull(10, 10, mineCount)).isNull()
    }
}
