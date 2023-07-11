package minesweeper.domain

import minesweeper.dto.GameBoardRequest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class MinesWeeperGameBoardTest {

    @Test
    fun `게임판은 높이와 너비를 입력받아서 게임판을 만든다`() {
        // given
        val height = 10
        val width = 10
        val board: Array<Array<GameBoardSquare>> =
            Array(height) { Array(width) { GameBoardSquare.NumberSquare.createEmpty() } }
        val listBoard = board.map { it.toList() }
        val gameBoardRequest = GameBoardRequest(height, width, 0)

        // when
        val actual = MinesWeeperGameBoard(gameBoardRequest).getBoard()

        // then
        Assertions.assertThat(actual).isEqualTo(listBoard)
    }
}
