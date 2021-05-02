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

        val result = assertThrows<UnsupportedOperationException> { mine.getSurroundingMineCount() }

        assertThat(result.message).isEqualTo(expectedMessage)
    }

    @Test
    fun `지뢰는 둘러싸는지뢰개수가 무조건 0이 아니다`() {
        val mine = Mine()
        val result = mine.isZero()
        assertThat(result).isEqualTo(false)
    }

    @Test
    fun `체크되지 않은 지뢰를 체크할 수 있다`() {
        val mine = Mine()
        val result = mine.open()
        assertThat(result.isChecked()).isEqualTo(true)
    }

    @Test
    fun `이미 체크한 지뢰를 체크하는 경우 예외가 발생한다`() {
        val mine = Mine(true)
        val expectedMessage = "이미 체크한 블록은 체크할 수 없습니다"
        val result = assertThrows<IllegalStateException> { mine.open() }
        assertThat(result.message).isEqualTo(expectedMessage)
    }
}
