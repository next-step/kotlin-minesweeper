package minesweeper.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class MineSweeperMakerTest {
    @Test
    fun `Unit Test`() {
        val candidate = (1..10).toMutableList()

        Assertions.assertThat(candidate.size).isEqualTo(10)
        candidate.remove(8)
        Assertions.assertThat(candidate).isEqualTo(9)
    }

    @Test
    fun `Maker Test`() {
        val maker = MineSweeperMaker()
        val minePositions = maker.getMinePosition(5,5,5)

        Assertions.assertThat(minePositions.size).isEqualTo(5)
    }
}
