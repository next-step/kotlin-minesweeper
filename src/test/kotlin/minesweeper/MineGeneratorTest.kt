package minesweeper

import minesweeper.domain.Ground
import minesweeper.domain.MineGenerator
import minesweeper.domain.RandomPositionGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineGeneratorTest {

    @Test
    fun `지뢰위치를_생성한다`() {
        val position = MineGenerator.generateMinePosition(RandomPositionGenerator(), Ground(5, 5))

        assertThat(position.x).isNotNull()
        assertThat(position.y).isNotNull()
    }
}