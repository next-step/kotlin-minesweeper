package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineTest {
    @Test
    fun has_mine() {
        val mine = Mine(Coordinate(1, 1))

        assertThat(mine.isMine()).isTrue()
    }
}
