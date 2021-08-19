package minesweeper.domain

import minesweeper.domain.Marker
import minesweeper.domain.MineGenerator
import minesweeper.domain.RandomPositionGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineGeneratorTest {

    @Test
    fun `지뢰위치를_생성한다`() {
        val mineGenerator = MineGenerator(Marker(5, 5))
        val positions = mineGenerator.generateMinePositions(RandomPositionGenerator(), 3)

        assertThat(positions.size).isEqualTo(3)
    }
}
