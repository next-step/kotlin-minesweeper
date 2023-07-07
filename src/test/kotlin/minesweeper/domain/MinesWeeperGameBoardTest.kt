package minesweeper.domain

import minesweeper.dto.GameBoardRequest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MinesWeeperGameBoardTest {

    @Test
    fun `게임판은 높이와 너비를 입력받아서 게임판을 만든다`() {
        // given
        val height = 10
        val width = 10
        val board: Array<Array<GameBoardSquare>> =
            Array(height) { Array(width) { GameBoardSquare(SquareValueType.EMPTY) } }
        val listBoard = board.map { it.toList() }

        // when
        val actual = MinesWeeperGameBoard(
            height,
            width,
            0
        ).getBoard()

        // then
        Assertions.assertThat(actual).isEqualTo(listBoard)
    }

    @Test
    fun `지뢰의 갯수가 전체 게임판 보다 크면 IllegaArgumentException을 throw 한다`() {
        val height = 10
        val width = 10
        val minesNumber = 101
        assertThrows<IllegalArgumentException> { MinesWeeperGameBoard(height, width, minesNumber) }
    }

    @Test
    fun `지뢰의 갯수가 전체 게임판 보다 크면 IllegaArgumentException을 throw 한다 - GameBoardRequest로 생성`() {
        val height = 10
        val width = 10
        val minesNumber = 101
        val gameBoardRequest = GameBoardRequest(height, width, minesNumber)
        assertThrows<IllegalArgumentException> { MinesWeeperGameBoard(gameBoardRequest) }
    }
}
