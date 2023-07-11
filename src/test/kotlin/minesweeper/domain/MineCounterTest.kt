package minesweeper.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MineCounterTest {

    @Test
    fun `게임판과 위치를 입력받아서 주변 8칸의 지뢰의 갯수가 몇개인지 카운트한다`() {
        // given
        val width = 3
        val height = 3
        val gameBoard: MutableList<MutableList<GameBoardSquare>> = MutableList(height) {
            MutableList(width) { GameBoardSquare.NumberSquare.createEmpty() }
        }

        /**
         * 지뢰판 모양
         * | *00
         * | 0**
         * | 000
         */
        val squareLocationLists = listOf(
            SquareLocation(0, 0),
            SquareLocation(1, 1),
            SquareLocation(2, 1)
        )

        squareLocationLists.forEach {
            val (x, y) = it
            gameBoard[y][x] = GameBoardSquare.MineSquare()
        }

        // when
        val actual = MineCounter.initMineCounts(gameBoard)

        /**
         * 숫자 계산후 지뢰판 모양
         * | *32
         * | 2**
         * | 122
         */
        val answerFirstRow = listOf(
            GameBoardSquare.MineSquare(),
            GameBoardSquare.NumberSquare(3),
            GameBoardSquare.NumberSquare(2),
        )
        val answerSecondRow = listOf(
            GameBoardSquare.NumberSquare(2),
            GameBoardSquare.MineSquare(),
            GameBoardSquare.MineSquare()
        )
        val answerThirdRow = listOf(
            GameBoardSquare.NumberSquare(1),
            GameBoardSquare.NumberSquare(2),
            GameBoardSquare.NumberSquare(2)
        )
        val answer = listOf(
            answerFirstRow,
            answerSecondRow,
            answerThirdRow
        )

        // then
        assertEquals(answer, actual, "The two lists of lists should be equal.")
    }
}
