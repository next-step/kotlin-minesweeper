package minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BoardTest {
    @Test
    fun `Board 는 높이, 너비를 이용하여 만들 수 있다`() {
        val board = Board(Height(10), Width(10))
        board.rows.size shouldBe 100
    }
}
