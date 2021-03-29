package domain.block

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class MineTest {

    @Test
    fun `지뢰를 생성한다`() {
        val mine = Mine()
        val result = mine.isMine()
        assertThat(result).isTrue()
    }

    @Test
    fun `주변지뢰개수를 요청하는 경우 예외가 발생한다`() {
        val mine = Mine()
        val expectedMessage = "지뢰는 주변지뢰의 개수를 구할 수 없습니다."

        val result = assertThrows<UnsupportedOperationException> { mine.surroundingMineCount() }

        assertThat(result.message).isEqualTo(expectedMessage)
    }
}
