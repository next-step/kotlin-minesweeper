package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinnerTest {

    @Test
    fun `지뢰를 클릭할 경우`() {
        val board = Board(
            boardSize = BoardSize(row = LengthOfSide(length = 10), col = LengthOfSide(length = 10)),
            mineIndexes = listOf(12, 41)
        )
        val gamer = Gamer(board)
        gamer.clickCoordinate(Coordinates(1, 2))

        assertThat(Winner.isLose(gamer)).isTrue()
    }

    @Test
    fun `게임에서 승리할 경우`() {
        val board = Board(
            boardSize = BoardSize(row = LengthOfSide(length = 10), col = LengthOfSide(length = 10)),
            mineIndexes = listOf(99)
        )
        val gamer = Gamer(board)
        gamer.clickCoordinate(Coordinates(0, 0))

        assertThat(Winner.isLose(gamer)).isFalse()
    }
}
