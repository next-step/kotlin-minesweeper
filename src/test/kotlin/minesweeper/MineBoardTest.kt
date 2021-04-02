package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineBoardTest {

    @Test
    fun `마인이 겹치지 않고 정해진 개수가 생성되는지 확인`() {
        val mineNumber = 5
        val mineBoard = MineBoard(5, 5, mineNumber)
        var boardMines = 0
        mineBoard.board.forEach {
            boardMines += it.count { it == " * " }
        }

        assertThat(mineNumber).isEqualTo(boardMines)
    }
}