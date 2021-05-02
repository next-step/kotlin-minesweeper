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
    fun `주변지뢰개수를 반환한다`(input: Int) {
        val nothing = Nothing(input)
        val result = nothing.getSurroundingMineCount()
        assertThat(result).isEqualTo(input)
    }

    @Test
    fun `체크되지 않은 땅을 체크할 수 있다`() {
        val dummyCount = SurroundingMineCount(0)
        val nothing = Nothing(dummyCount)

        val result = nothing.check()

        assertThat(result.isChecked()).isEqualTo(true)
    }

    @Test
    fun `이미 체크한 땅을 체크하는 경우 예외가 발생한다`() {
        val dummyCount = SurroundingMineCount(0)
        val nothing = Nothing(dummyCount, true)
        val expectedMessage = "이미 체크한 블록은 체크할 수 없습니다"

        val result = assertThrows<IllegalStateException> { nothing.check() }

        assertThat(result.message).isEqualTo(expectedMessage)
    }
}
