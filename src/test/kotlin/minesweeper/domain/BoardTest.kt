package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class BoardTest {

    @Test
    fun `board 생성`() {
        val boardSpec = BoardSpec(
            width = NaturalNumber(2),
            height = NaturalNumber(2),
            mineCount = NaturalNumber(1)
        )

        val board = Board.createBoard(boardSpec, listOf(Position(0, 0)))
        assertThat(board.cells.get(Position(0, 0))).isInstanceOf(MineCell::class.java)
    }

    @Test
    fun `expose 하면 지뢰가 없는 인접한 cell이 모두 expose 된다`() {
        val boardSpec = BoardSpec(
            width = NaturalNumber(3),
            height = NaturalNumber(3),
            mineCount = NaturalNumber(2)
        )

        val board = Board.createBoard(boardSpec, listOf(Position(2, 0), Position(2, 1)))
        board.expose(Position(0, 0))

        Assertions.assertTrue(board.cells[Position(0, 0)]!!.covered)
        Assertions.assertTrue(board.cells[Position(0, 1)]!!.covered)
        Assertions.assertTrue(board.cells[Position(0, 2)]!!.covered)
        Assertions.assertTrue(board.cells[Position(1, 0)]!!.covered)
        Assertions.assertTrue(board.cells[Position(1, 1)]!!.covered)
        Assertions.assertTrue(board.cells[Position(1, 2)]!!.covered)

        Assertions.assertFalse(board.cells[Position(2, 0)]!!.covered)
        Assertions.assertFalse(board.cells[Position(2, 2)]!!.covered)
        Assertions.assertFalse(board.cells[Position(2, 2)]!!.covered)
    }

    @Test
    fun `gameSate WIN`() {
        val boardSpec = BoardSpec(
            width = NaturalNumber(2),
            height = NaturalNumber(2),
            mineCount = NaturalNumber(1)
        )

        val board = Board.createBoard(boardSpec, listOf(Position(1, 1)))
        assertThat(board.expose(Position(0, 0))).isEqualTo(GameState.RUNNING)
        assertThat(board.expose(Position(0, 1))).isEqualTo(GameState.RUNNING)
        assertThat(board.expose(Position(1, 0))).isEqualTo(GameState.WIN)
    }

    @Test
    fun `gameSate LOSE`() {
        val boardSpec = BoardSpec(
            width = NaturalNumber(2),
            height = NaturalNumber(2),
            mineCount = NaturalNumber(1)
        )

        val board = Board.createBoard(boardSpec, listOf(Position(1, 1)))
        assertThat(board.expose(Position(1, 1))).isEqualTo(GameState.LOSE)
    }

    @Test
    fun `gameSate RUNNING`() {
        val boardSpec = BoardSpec(
            width = NaturalNumber(3),
            height = NaturalNumber(3),
            mineCount = NaturalNumber(2)
        )

        val board = Board.createBoard(boardSpec, listOf(Position(1, 2), Position(2, 2)))
        assertThat(board.expose(Position(0, 0))).isEqualTo(GameState.RUNNING)
    }
}
