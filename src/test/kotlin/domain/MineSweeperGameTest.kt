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

    @Test
    fun `게임은 지뢰의 리스트를 가진다`() {
        val mines = listOf(Mine(0, 0), Mine(1, 0))
        val minesweeperGame = MineSweeperGame(5, 5, mines)
        assertThat(minesweeperGame.mineList).containsExactly(Mine(0, 0), Mine(1, 0))
    }
}
