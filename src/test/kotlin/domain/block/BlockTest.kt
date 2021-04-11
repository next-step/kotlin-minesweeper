package domain.block

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class BlockTest {

    @Test
    fun `블록 생성 시 체크되지 않은 상태이다`() {
        val block = DummyBlock()
        val expected = false
        assertThat(block.isChecked()).isEqualTo(expected)
    }

    @Test
    fun `체크되지 않은 블록을 체크할 수 있다`() {
        val block = DummyBlock()
        val expected = true

        block.check()

        assertThat(block.isChecked()).isEqualTo(expected)
    }

    @Test
    fun `체크된 블록을 체크하는 경우 예외가 발생한다`() {
        val block = DummyBlock().apply { check() }
        val expectedMessage = "이미 체크한 블록은 체크할 수 없습니다"

        val result = assertThrows<IllegalStateException> { block.check() }

        assertThat(result.message).isEqualTo(expectedMessage)
    }
}

class DummyBlock : Block() {
    override fun isMine() = throw UnsupportedOperationException("this is dummy")
    override fun surroundingMineCount() = throw UnsupportedOperationException("this is dummy")
}
