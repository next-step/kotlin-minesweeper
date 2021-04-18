package domain

import domain.square.mine.Mine
import domain.square.mine.MineFactory
import model.GameData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BoardTest {
    @Test
    fun `보드를 생성한다`() {
        val gameData = GameData(3, 3, 2)
        val mineFactory = MineFactory(TestPositionIdFactory(listOf(0, 4))) // [0, 0]. [1, 1]
        val board = Board(gameData, mineFactory)

        assertThat(board.squares[0][0]).isInstanceOf(Mine::class.java)
        assertThat(board.squares[1][1]).isInstanceOf(Mine::class.java)

        assertThat(board.squares[0][1].mineCountAround).isEqualTo(2)
        assertThat(board.squares[0][2].mineCountAround).isEqualTo(1)
        assertThat(board.squares[1][0].mineCountAround).isEqualTo(2)
        assertThat(board.squares[1][2].mineCountAround).isEqualTo(1)
        assertThat(board.squares[2][0].mineCountAround).isEqualTo(1)
        assertThat(board.squares[2][1].mineCountAround).isEqualTo(1)
        assertThat(board.squares[2][2].mineCountAround).isEqualTo(1)
    }
}
