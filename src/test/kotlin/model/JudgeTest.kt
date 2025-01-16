package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class JudgeTest {

    private lateinit var initialSquares: Array<Array<Square>>

    @BeforeEach
    fun setUp() {
        initialSquares = arrayOf(
            arrayOf(Square(SquareType.TILE), Square(SquareType.TILE), Square(SquareType.MINE)),
            arrayOf(Square(SquareType.TILE), Square(SquareType.TILE), Square(SquareType.MINE)),
            arrayOf(Square(SquareType.MINE), Square(SquareType.MINE), Square(SquareType.TILE))
        )
    }

    @Test
    fun `특정 좌표 선택이 가능하다`() {
        val board = Board(initialSquares)
        val judge = Judge(board, 3, 3)
        val squareType = judge.clickSquare(1, 1)
        assertThat(squareType).isEqualTo(SquareType.TILE)
    }

    @Test
    fun `선택한 좌표가 지뢰면 게임에 패배한다`() {
        val board = Board(initialSquares)
        val judge = Judge(board, 3, 3)
        val mineSquareType = judge.clickSquare(2, 3)
        assertThat(mineSquareType).isEqualTo(SquareType.MINE)
    }

    @Test
    fun `선택한 좌표가 지뢰가 아니고 인접한 칸에 지뢰가 없으면 인접한 칸이 모두 열린다`() {
        /*
            0 2 C
            2 4 C
            C C 2
         */
        val boardHeight = BoardHeight(3)
        val boardWidth = BoardWidth(3)
        val mines = listOf(2, 5, 6)
        val board = Board.createBoard(boardHeight, boardWidth, mines)
        val judge = Judge(board, 3, 3)

        judge.clickSquare(1, 1)

        assertThat(judge.squares[0][0].isOpen).isTrue
        assertThat(judge.squares[0][1].isOpen).isTrue
        assertThat(judge.squares[0][2].isOpen).isFalse
        assertThat(judge.squares[1][0].isOpen).isTrue
        assertThat(judge.squares[1][1].isOpen).isTrue
        assertThat(judge.squares[1][2].isOpen).isFalse
        assertThat(judge.squares[2][0].isOpen).isFalse
        assertThat(judge.squares[2][1].isOpen).isFalse
        assertThat(judge.squares[2][2].isOpen).isFalse
    }

    @Test
    fun `선택한 좌표가 지뢰가 아니고 인접한 칸에 지뢰가 있으면 선택한 칸만 열린다`() {
        /*
            2 C 2
            3 C 2
            C 2 1
        */
        val boardHeight = BoardHeight(3)
        val boardWidth = BoardWidth(3)
        val mines = listOf(1, 4, 6)
        val board = Board.createBoard(boardHeight, boardWidth, mines)
        val judge = Judge(board, 3, 3)

        judge.clickSquare(1, 1)

        assertThat(judge.squares[0][0].isOpen).isTrue
        assertThat(judge.squares[0][1].isOpen).isFalse
        assertThat(judge.squares[0][2].isOpen).isFalse
        assertThat(judge.squares[1][0].isOpen).isFalse
        assertThat(judge.squares[1][1].isOpen).isFalse
        assertThat(judge.squares[1][2].isOpen).isFalse
        assertThat(judge.squares[2][0].isOpen).isFalse
        assertThat(judge.squares[2][1].isOpen).isFalse
        assertThat(judge.squares[2][2].isOpen).isFalse
    }
}
