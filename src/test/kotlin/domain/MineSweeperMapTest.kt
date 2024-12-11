package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineSweeperMapTest {
    @Test
    fun isMineTest() {
        val mineGenerator = { _: Int, _: Int, _: Int -> MineSet(setOf(Mine(0, 0), Mine(1, 0))) }
        val game = MineSweeperGame.makeGame(5, 5, 2, mineGenerator)
        val map = MineSweeperMap(game.mines.mines.associate { Pair(MinePosition(it.getXPosition(), it.getYPosition()), true) })
        assertThat(map.isMine(0, 0)).isTrue()
        assertThat(map.isMine(0, 1)).isFalse()
    }
}
