package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineMapTest {
    @Test
    fun isMineTest() {
        val mineGenerator = { _: Int, _: Int, _: Int -> MineSet(setOf(Mine(0, 0), Mine(1, 0))) }
        val game = MineSweeperGame.makeGame(5, 5, 2, mineGenerator)
        val map = game.getMineMap()
        assertThat(map.isMine(0, 0)).isTrue()
        assertThat(map.isMine(0, 1)).isFalse()
    }
}
