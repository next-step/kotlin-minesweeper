package domain

import domain.position.Position
import domain.position.TestPositionIdFactory
import domain.square.mine.Mine
import domain.square.mine.MineFactory
import domain.square.mine.Mines
import model.GameData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BoardTest {
    @Test
    fun `보드를 생성한다`() {
        val gameData = GameData(3, 3, 2)
        val mineFactory = MineFactory(TestPositionIdFactory(listOf(0, 4))) // [0, 0]. [1, 1]
        val board = Board(mineFactory.createMines(gameData), gameData)

        assertThat(board.squares.get(Position(0, 0))).isInstanceOf(Mine::class.java)
        assertThat(board.squares.get(Position(1, 1))).isInstanceOf(Mine::class.java)

        assertThat(board.squares.get(Position(0, 1)).mineCountAround).isEqualTo(2)
        assertThat(board.squares.get(Position(0, 2)).mineCountAround).isEqualTo(1)
        assertThat(board.squares.get(Position(1, 0)).mineCountAround).isEqualTo(2)
        assertThat(board.squares.get(Position(1, 2)).mineCountAround).isEqualTo(1)
        assertThat(board.squares.get(Position(2, 0)).mineCountAround).isEqualTo(1)
        assertThat(board.squares.get(Position(2, 1)).mineCountAround).isEqualTo(1)
        assertThat(board.squares.get(Position(2, 2)).mineCountAround).isEqualTo(1)
    }

    @Test
    fun `특정 위치가 열려있는지 확인한다`() {
        val row = 0
        val col = 0

        val gameData = GameData(3, 3, 0)
        val board = Board(Mines(listOf()), gameData)

        val resultNotOpened = board.hasOpened(Position(row, col))
        assertThat(resultNotOpened).isFalse()

        board.squares.get(Position(row, col)).open()

        val resultOpened = board.hasOpened(Position(row, col))
        assertThat(resultOpened).isTrue()
    }

    @Test
    fun `특정 위치가 mine인지 확인한다`() {
        val gameData = GameData(3, 3, 2)
        val mineFactory = MineFactory(TestPositionIdFactory(listOf(0, 4))) // [0, 0]. [1, 1]
        val board = Board(mineFactory.createMines(gameData), gameData)

        val resultMine1 = board.isMine(Position(0, 0))
        assertThat(resultMine1).isTrue()

        val resultMine2 = board.isMine(Position(1, 1))
        assertThat(resultMine2).isTrue()

        val resultNotMine = board.isMine(Position(0, 1))
        assertThat(resultNotMine).isFalse()
    }

    @Test
    fun `mine이 주변에 있는지 확인한다`() {
        val gameData = GameData(3, 3, 2)
        val mineFactory = MineFactory(TestPositionIdFactory(listOf(0, 1))) // [0, 0]. [0, 1]
        val board = Board(mineFactory.createMines(gameData), gameData)

        val resultMineAround = board.hasNoMineAround(Position(1, 0))
        assertThat(resultMineAround).isFalse()

        val resultMineNotAround = board.hasNoMineAround(Position(2, 2))
        assertThat(resultMineNotAround).isTrue()
    }

    @Test
    fun `mine을 제외한 모든 square가 open인지 확인한다`() {
        val gameData = GameData(2, 2, 2)
        val mineFactory = MineFactory(TestPositionIdFactory(listOf(0, 1))) // [0, 0]. [0, 1]
        val board = Board(mineFactory.createMines(gameData), gameData)

        val resultFalse = board.hasAllOpened()
        assertThat(resultFalse).isFalse()

        board.squares.get(Position(1, 0)).open()
        board.squares.get(Position(1, 1)).open()

        val resultTrue = board.hasAllOpened()
        assertThat(resultTrue).isTrue()
    }

    @Test
    fun `특정 square를 open한다`() {
        val gameData = GameData(2, 2, 0)
        val board = Board(Mines(listOf()), gameData)

        assertThat(board.squares.get(Position(0, 0)).isOpen).isFalse()

        board.openSquare(Position(0, 0))

        assertThat(board.squares.get(Position(0, 0)).isOpen).isTrue()
    }
}
