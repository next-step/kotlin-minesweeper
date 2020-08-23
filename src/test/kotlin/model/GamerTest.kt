package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GamerTest {

    @Test
    fun `클릭한 좌표의 값이 0인 경우`() {
        val board = Board(
            boardSize = BoardSize(row = LengthOfSide(length = 10), col = LengthOfSide(length = 10)),
            mineIndexes = listOf(12, 41)
        )
        val gamer = Gamer(board)
        gamer.clickCoordinate(Coordinates(0, 0))

        assertThat(gamer.board.value.filterNot { it.value == MineType.NONE }.count()).isEqualTo(8)
    }

    @Test
    fun `클릭한 좌표의 값이 1인 경우`() {
        val board = Board(
            boardSize = BoardSize(row = LengthOfSide(length = 10), col = LengthOfSide(length = 10)),
            mineIndexes = listOf(12, 41)
        )
        val gamer = Gamer(board)
        gamer.clickCoordinate(Coordinates(0, 1))

        assertThat(gamer.board.value.filter { it.value == MineType.ONE }.count()).isEqualTo(1)
    }
}
