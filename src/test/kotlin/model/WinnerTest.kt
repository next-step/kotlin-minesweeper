package model

import org.assertj.core.api.Assertions
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

        Assertions.assertThat(Winner.isLose(gamer)).isTrue()
    }
}

object Winner {

    fun isLose(gamer: Gamer) = gamer.gameBoard.containsValue(MineType.MINE)
}
