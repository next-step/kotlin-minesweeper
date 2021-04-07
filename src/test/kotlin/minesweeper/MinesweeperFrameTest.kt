package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MinesweeperFrameTest {

    @Test
    fun `지뢰 프레임 확인`() {
        val minesweeperFrame = MinesweeperFrame(10, 5, 10)
        assertThat(minesweeperFrame.minePanel.size).isEqualTo(10)
        assertThat(minesweeperFrame.minePanel[0].size).isEqualTo(5)
    }
}
