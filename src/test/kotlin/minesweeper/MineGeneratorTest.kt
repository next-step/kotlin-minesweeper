package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineGeneratorTest {

    @Test
    fun `지뢰위치를_생성한다`() {
        val position = MineGenerator.generateMinePosition()

        assertThat(position.x).isNotNull()
        assertThat(position.y).isNotNull()
    }
}