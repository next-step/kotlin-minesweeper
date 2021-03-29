package domain.block

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
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
    fun `주변 지뢰의 개수가 0~8 사이 값으로 생성할 수 있다`(surroundingMineCount: Int) {
        val nothing = Nothing(surroundingMineCount)
        assertThat(nothing).isNotNull
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 9])
    fun `주변 지뢰의 개수가 0~8 사이의 값이 아닌 경우 예외를 반환한다`(surroundingMineCount: Int) {
        val expected = "주변 지뢰의 개수는 0~8 사이의 값이어야 합니다. surroundingMineCount: $surroundingMineCount"
        val result = assertThrows<IllegalArgumentException> { Nothing(surroundingMineCount) }
        assertThat(result.message).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 8])
    fun `주변지뢰개수를 반환한다`(surroundingMineCount: Int) {
        val nothing = Nothing(surroundingMineCount)
        val result = nothing.surroundingMineCount()
        assertThat(result).isEqualTo(surroundingMineCount)
    }
    
}
