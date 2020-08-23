package minesweeper.model

import minesweeper.model.block.Block
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class MineCounterTest {
    @Test
    fun `Mine getNumber Test`() {
        val mine = Block(Type.from(-1))
        val empty = Block(Type.from(0))
        val board = Board(listOf(listOf(empty, mine), listOf(mine, mine)))

        Assertions.assertThat(NearByMineCounter().getMineNumber(0, board)).isEqualTo(3)
    }
}
