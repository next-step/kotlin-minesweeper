package minesweeper.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class MinesWeeperGameBoardTest {

    @Test
    fun `게임판은 높이와 너비를 입력받아서 게임판을 만든다`() {
        val height = 10
        val width = 10
        val answer = Array(height) { Array(width) { 'C' } }
        val actual = MinesWeeperGameBoard(
            height,
            width,
            0,
            RandomMineLocationCoordinateGenerator()
        ).getBoard()

        Assertions.assertThat(actual).isEqualTo(answer)
    }
}
