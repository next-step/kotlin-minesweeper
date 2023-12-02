package minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BoardTest {
    @Test
    fun `Board 는 높이, 너비를 이용하여 만들 수 있다`() {
        val board = Board(Height(10), Width(10), MineCount(1))
        board.rows.size shouldBe 100
    }

    @Test
    fun `Board 는 입력받은 지뢰 개수만큼 지뢰를 가지고 있다`() {
        val board = Board(Height(10), Width(10), MineCount(10))
        board.rows.flatMap { it.cells }.count { cell -> cell.state == State.MINE } shouldBe 10
    }
}
