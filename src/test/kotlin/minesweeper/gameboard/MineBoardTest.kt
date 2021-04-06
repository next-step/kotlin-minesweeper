package minesweeper.gameboard

import minesweeper.domain.gameboard.MineBoard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MineBoardTest {

    @Test
    fun `보드를 만들면서 마인의 개수가 많으면 Exception 발생`() {
        assertThrows<IllegalArgumentException> { MineBoard(4, 4, 16) }
    }

    @Test
    fun `마인이 겹치지 않고 정해진 개수가 생성되는지 확인`() {
        val height = 5
        val width = 5
        val mineNumber = 5

        val mineBoard = MineBoard(width, height, mineNumber)
        var boardMines = 0
        repeat(height) { row ->
            repeat(width) { col ->
                if (mineBoard.board[row].getColumnBlock(col).isMine()) boardMines++
            }
        }

        assertThat(mineNumber).isEqualTo(boardMines)
    }
}
