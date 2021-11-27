package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BoardTest {

    @Test
    fun `보드는 Width와 Height를 가진다`() {
        val board = Board(width = 5, height = 10)
        assertAll(
            { assertThat(board.width).isEqualTo(Width(5)) },
            { assertThat(board.height).isEqualTo(Height(10)) },
        )
    }
}
