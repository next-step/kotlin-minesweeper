package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CellTest {
    @Test
    fun `Cell이 Mine인지 Block인지 판별`() {
        val board = listOf(Block, Block, Mine)
        assertThat(board[0].isMine()).isFalse
        assertThat(board[1].isMine()).isFalse
        assertThat(board[2].isMine()).isTrue
    }
}
