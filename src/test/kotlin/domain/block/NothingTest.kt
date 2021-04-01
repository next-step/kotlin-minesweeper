package domain.block

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class NothingTest {

    @Test
    fun `지뢰가 아니다`() {
        val nothing = Nothing()
        val result = nothing.isMine()
        assertThat(result).isFalse()
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 8])
    fun `주변지뢰개수를 반환한다`(input: Int) {
        val nothing = Nothing(input)
        val expected = SurroundingMineCount(input)

        val result = nothing.surroundingMineCount()

        assertThat(result).isEqualTo(expected)
    }
}
