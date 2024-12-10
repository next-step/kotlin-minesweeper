package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineSweeperGameTest {
    @Test
    fun `지뢰찾기 게임은 너비와 높이를 가진다`() {
        val minesweeperGame = MineSweeperGame.makeGame(5, 5, 5)
        assertThat(minesweeperGame.getWidth()).isEqualTo(5)
        assertThat(minesweeperGame.getHeight()).isEqualTo(5)
    }

    @Test
    fun `게임은 지뢰의 리스트를 가진다`() {
        val mines = MineSet(setOf(Mine(0, 0), Mine(1, 0)))
        val minesweeperGame = MineSweeperGame(MineShape(5, 5), mines)
        assertThat(minesweeperGame.mines.mines).containsExactly(Mine(0, 0), Mine(1, 0))
    }

    @Test
    fun `게임은 지뢰의 위치를 결정하는 역할을 가진다`() {
        val mineGenerator = { _: Int, _: Int, _: Int -> MineSet(setOf(Mine(0, 0), Mine(1, 0))) }
        val mineSweeperGame = MineSweeperGame.makeGame(5, 5, 2, mineGenerator)
        assertThat(mineSweeperGame.mines.mines).containsExactly(Mine(0, 0), Mine(1, 0))
    }
}
