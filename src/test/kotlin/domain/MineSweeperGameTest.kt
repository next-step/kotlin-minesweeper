package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineSweeperGameTest {
    @Test
    fun `지뢰찾기 게임은 너비와 높이를 가진다`() {
        val minesweeperGame = MineSweeperGame(5, 5)
        assertThat(minesweeperGame.width).isEqualTo(5)
        assertThat(minesweeperGame.height).isEqualTo(5)
    }
}
