package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

internal class BoardTest {
    @Test
    fun `보드의 높이, 너비, 지뢰 개수가 양수가 아닌 경우`() {
        assertThrows<IllegalArgumentException> { Board(-1, -1, -1) }
        assertThrows<IllegalArgumentException> { Board(10, 10, 0) }
    }

    @Test
    fun `설정한 지뢰 개수만큼 지뢰 위치를 생성하는지 테스트`() {
        val board = Board(10, 10, 10)
        val minesPosition = board.getMinesPosition()
        assertThat(minesPosition.distinct().size).isEqualTo(10)
    }

    @Test
    fun `설정한 지뢰 위치에 지뢰를 옳게 생성하는지 테스트`() {
        val minesPosition = listOf(0, 1, 2)
        val board = Board(3, 3, 3).setMinesAndBlocks(minesPosition)
        assertThat(board[0]).isInstanceOf(Mine::class.java)
        assertThat(board[1]).isInstanceOf(Mine::class.java)
        assertThat(board[2]).isInstanceOf(Mine::class.java)
        assertThat(board[3]).isInstanceOf(Block::class.java)
    }

    @Test
    fun `설치할 지뢰 위치의 개수가 초기에 설정한 지뢰 개수와 일치하지 않는 경우`() {
        val minesPosition = listOf(0)
        val board = Board(3, 3, 2)
        assertThrows<IllegalArgumentException> { board.setMinesAndBlocks(minesPosition) }
    }

    @Test
    fun `주변의 지뢰 개수를 옳게 구하는지 테스트`() {
        val minesPosition = listOf(0, 1)
        val board = Board(3, 3, 2)
        board.setMinesAndBlocks(minesPosition)
        // 설정한 게임 보드
        //     * * 1
        //     2 2 1
        //     0 0 0

        assertThat(board.getAroundMineCount(2, minesPosition)).isEqualTo(1)
        assertThat(board.getAroundMineCount(3, minesPosition)).isEqualTo(2)
        assertThat(board.getAroundMineCount(4, minesPosition)).isEqualTo(2)
        assertThat(board.getAroundMineCount(5, minesPosition)).isEqualTo(1)
        assertThat(board.getAroundMineCount(6, minesPosition)).isEqualTo(0)
        assertThat(board.getAroundMineCount(7, minesPosition)).isEqualTo(0)
        assertThat(board.getAroundMineCount(8, minesPosition)).isEqualTo(0)
    }
}
