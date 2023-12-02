package minesweeper.domain

import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BoardTest {
    @Test
    fun `Board 는 높이 개수의 Row를 이용하여 만들 수 있다`() {
        val board = Board(BoardInformation(Height(10), Width(10), MineCount(1)))
        board.mineMap.size shouldBe 10
    }

    @Test
    fun `Board 는 입력받은 지뢰 개수만큼 지뢰를 가지고 있다`() {
        val board = Board(BoardInformation(Height(10), Width(10), MineCount(10)))
        board.mineMap.flatMap { it.cells }.count { cell -> cell.state == State.MINE } shouldBe 10
    }

    @Test
    fun `Board 크기보다 지뢰의 숫자가 많으면 IllegalArgumentException 발생한다`() {
        assertThrows<IllegalArgumentException> { Board(BoardInformation(Height(10), Width(10), MineCount(101))) }
    }

    @Test
    fun `보드에서 원하는 위치에 지뢰를 심을 수 있다`() {
        val minePoint1 = Point(0, 0)
        val minePoint2 = Point(5, 5)
        val minePoint3 = Point(7, 7)
        val strategy = ManualMinePlacementStrategy(listOf(minePoint1, minePoint2, minePoint3))
        val board = Board(BoardInformation(Height(10), Width(10), MineCount(3)), strategy)
        val mineCells = board.mineMap.flatMap { it.cells }.filter { cell -> cell.state == State.MINE }
        mineCells.size shouldBe 3
        mineCells.shouldContain(Cell(minePoint1, State.MINE))
        mineCells.shouldContain(Cell(minePoint2, State.MINE))
        mineCells.shouldContain(Cell(minePoint3, State.MINE))
    }
}
