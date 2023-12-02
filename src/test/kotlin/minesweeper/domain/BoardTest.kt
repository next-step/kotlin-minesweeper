package minesweeper.domain

import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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

    @Test
    fun `Board 크기보다 지뢰의 숫자가 많으면 IllegalArgumentException 발생한다`() {
        assertThrows<IllegalArgumentException> { Board(Height(10), Width(10), MineCount(101)) }
    }

    @Test
    fun `보드에서 원하는 위치에 지뢰를 심을 수 있다`() {
        val strategy = ManualMinePlacementStrategy(listOf(0, 1, 2, 3, 4, 10, 11, 12, 13, 14))
        val board = Board(Height(10), Width(10), MineCount(10), strategy)
        val mineCells = board.rows.flatMap { it.cells }.filter { cell -> cell.state == State.MINE }
        mineCells.size shouldBe 10
        mineCells.shouldContain(Cell(Point(0, 0), State.MINE))
        mineCells.shouldContain(Cell(Point(1, 0), State.MINE))
        mineCells.shouldContain(Cell(Point(0, 1), State.MINE))
    }
}
