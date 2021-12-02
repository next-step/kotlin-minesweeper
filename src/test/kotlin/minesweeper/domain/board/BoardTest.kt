package minesweeper.domain.board

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BoardTest {

    @Test
    fun `가로 10 세로 10 지뢰 5개인 보드를 만들 경우 100개의 셀과 5개의 지뢰가 만들어 진다`() {
        // given
        val boardSize = BoardSize(10, 10)
        val mineCount = 5
        val board = Board.of(boardSize, mineCount)

        // when
        val cellCounts = board.cells.size
        val boardMineCount = board.cells.filter { it.state.value == -1 }.size

        // then
        assertAll({
            assertThat(cellCounts).isEqualTo(100)
            assertThat(boardMineCount).isEqualTo(mineCount)
        })
    }
}
