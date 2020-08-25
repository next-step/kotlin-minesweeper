package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BlockTest {

    @Test
    fun `일반 블록은 주변에 있는 지뢰 개수를 가진다`() {
        val general = NormalBlock().apply { increaseMineCount() }
        val mine = Mine().apply { increaseMineCount() }
        assertThat(general.mineCount).isEqualTo(1)
        assertThat(mine.mineCount).isEqualTo(-1)
    }

    @Test
    fun `사용자가 공개할 블록을 선택할 수 있다`() {
        val block = NormalBlock().apply { open() }
        assertThat(block.isOpened).isEqualTo(true)
    }
}
