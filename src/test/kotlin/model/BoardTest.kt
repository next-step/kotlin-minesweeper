package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardTest {

    @Test
    fun `보드에 지뢰 설치하기`() {
        val boardSize = BoardSize(LengthOfSide(10), LengthOfSide(10))
        val board = Board(boardSize, listOf(1, 2, 3))

        assertThat(board.getMineCoordinates().size).isEqualTo(3)
    }

    @Test
    fun `지뢰 좌표 구하기`() {
        val boardSize = BoardSize(LengthOfSide(10), LengthOfSide(10))
        val board = Board(boardSize, listOf(11))

        assertThat(board.getMineCoordinates().first()).isEqualTo(Coordinates(1, 1))
    }

    @Test
    fun `왼쪽 상단에 지뢰가 있는 경우`() {
        val boardSize = BoardSize(LengthOfSide(10), LengthOfSide(10))
        val board = Board(boardSize, listOf(0))
        val mineCountBoard = board.getMineCountMap()

        assertThat(mineCountBoard[Coordinates(0, 1)]).isEqualTo(MineType.ONE)
        assertThat(mineCountBoard[Coordinates(1, 0)]).isEqualTo(MineType.ONE)
        assertThat(mineCountBoard[Coordinates(1, 1)]).isEqualTo(MineType.ONE)
    }

    @Test
    fun `오른쪽 상단에 지뢰가 있는 경우`() {
        val boardSize = BoardSize(LengthOfSide(10), LengthOfSide(10))
        val board = Board(boardSize, listOf(9))
        val mineCountBoard = board.getMineCountMap()

        assertThat(mineCountBoard[Coordinates(0, 8)]).isEqualTo(MineType.ONE)
        assertThat(mineCountBoard[Coordinates(1, 8)]).isEqualTo(MineType.ONE)
        assertThat(mineCountBoard[Coordinates(1, 9)]).isEqualTo(MineType.ONE)
    }

    @Test
    fun `왼쪽 하단에 지뢰가 있는 경우`() {
        val boardSize = BoardSize(LengthOfSide(10), LengthOfSide(10))
        val board = Board(boardSize, listOf(90))
        val mineCountBoard = board.getMineCountMap()

        assertThat(mineCountBoard[Coordinates(8, 0)]).isEqualTo(MineType.ONE)
        assertThat(mineCountBoard[Coordinates(8, 1)]).isEqualTo(MineType.ONE)
        assertThat(mineCountBoard[Coordinates(9, 1)]).isEqualTo(MineType.ONE)
    }

    @Test
    fun `오른쪽 하단에 지뢰가 있는 경우`() {
        val boardSize = BoardSize(LengthOfSide(10), LengthOfSide(10))
        val board = Board(boardSize, listOf(99))
        val mineCountBoard = board.getMineCountMap()

        assertThat(mineCountBoard[Coordinates(9, 8)]).isEqualTo(MineType.ONE)
        assertThat(mineCountBoard[Coordinates(8, 8)]).isEqualTo(MineType.ONE)
        assertThat(mineCountBoard[Coordinates(8, 9)]).isEqualTo(MineType.ONE)
    }
}
