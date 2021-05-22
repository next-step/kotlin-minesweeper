package domain

import domain.position.Position
import domain.position.TestPositionIdFactory
import domain.square.mine.MineFactory
import model.GameData
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class SquaresTest {

    @Test
    fun `mine을 제외한 모든 square가 open인지 확인한다`() {
        val gameData = GameData(2, 2, 2)
        val mineFactory = MineFactory(TestPositionIdFactory(listOf(0, 1))) // [0, 0]. [0, 1]
        val board = Board(mineFactory.createMines(gameData), gameData)
        val squares = board.squares

        val resultFalse = squares.hasAllOpened()
        Assertions.assertThat(resultFalse).isFalse()

        squares.get(Position(1, 0)).open()
        squares.get(Position(1, 1)).open()

        val resultTrue = squares.hasAllOpened()
        Assertions.assertThat(resultTrue).isTrue()
    }
}
