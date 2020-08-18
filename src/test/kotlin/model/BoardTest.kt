package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardTest {

    @Test
    fun `보드에 지뢰 설치하기`() {
        val boardSize = BoardSize(LengthOfSide(10), LengthOfSide(10))
        val board = Board(boardSize, listOf(1, 2, 3))

        assertThat(board.grid.flatten().count { it == MineType.MINE.symbol }).isEqualTo(3)
    }

    @Test
    fun `지뢰 좌표 구하기`() {
        val boardSize = BoardSize(LengthOfSide(10), LengthOfSide(10))
        val board = Board(boardSize, listOf(11))

        assertThat(board.getMineCoordinates().first()).isEqualTo(Coordinates(1, 1))
    }
}
