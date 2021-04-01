package domain.block

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BlockFactoryTest {

    @Test
    fun `지뢰인 경우 Mine을 생성한다`() {
        val result = BlockFactory.create(isMine = true)
        assertThat(result).isInstanceOf(Mine::class.java)
    }

    @Test
    fun `지뢰인 경우 Nothing을 생성한다`() {
        val expected = Nothing()
        val result = BlockFactory.create(isMine = false)
        assertThat(result).isEqualTo(expected)
    }
}
