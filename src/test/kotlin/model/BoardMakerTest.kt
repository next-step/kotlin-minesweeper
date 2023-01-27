package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BoardMakerTest {
    @Test
    fun `입력 받은 높이, 너비, 지뢰 개수와 일치하는 보드가 생성된다`() {
        val board = BoardMaker().run(BoardHeight(5), BoardWidth(5), Mine(5))
        val tiles = board.filter {
            it == TILE
        }
        val mines = board.filter {
            it == MINE
        }
        val actual = board.size
        val expected = tiles.size + mines.size

        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        private const val TILE = "C"
        private const val MINE = "*"
    }
}
