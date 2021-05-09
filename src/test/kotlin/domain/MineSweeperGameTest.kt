package domain

import domain.position.Position
import domain.position.TestPositionIdFactory
import domain.square.mine.MineFactory
import model.GameData
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MineSweeperGameTest {

    @Test
    fun `이미 열려있는 위치를 open하려는 경우 오류 발생`() {
        val gameData = GameData(3, 3, 0)
        val mineSweeperGame = MineSweeperGame(gameData)

        mineSweeperGame.open(Position(0, 0))
        Assertions.assertThatThrownBy { mineSweeperGame.open(Position(0, 0)) }
            .isInstanceOf(IllegalStateException::class.java)
    }

    @Test
    fun `open한 위치가 mine인 경우 패배 확인`() {
        val gameData = GameData(3, 3, 1)
        val mineFactory = MineFactory(TestPositionIdFactory(listOf(0))) // [0, 0]
        val mineSweeperGame = MineSweeperGame(gameData, mineFactory)

        mineSweeperGame.open(Position(0, 0))

        assertThat(mineSweeperGame.hasDone).isTrue()
        assertThat(mineSweeperGame.hasLose).isTrue()
    }

    @Test
    fun `특정 위치가 open할 수 있는 곳인 경우 open 확인`() {
        val gameData = GameData(2, 2, 1)
        val mineFactory = MineFactory(TestPositionIdFactory(listOf(0))) // [0, 0]
        val mineSweeperGame = MineSweeperGame(gameData, mineFactory)

        mineSweeperGame.open(Position(1, 1))

        assertThat(mineSweeperGame.board.squares.get(Position(1, 1)).isOpen).isTrue()
    }

    @Test
    fun `특정 위치가 mine이 아니고 주변에 mine이 없는 경우 주변 open`() {
        val gameData = GameData(3, 2, 1)
        val mineFactory = MineFactory(TestPositionIdFactory(listOf(0))) // [0, 0]
        val mineSweeperGame = MineSweeperGame(gameData, mineFactory)

        mineSweeperGame.open(Position(0, 2))

        assertThat(mineSweeperGame.board.squares.get(Position(0, 2)).isOpen).isTrue()
        assertThat(mineSweeperGame.board.squares.get(Position(0, 1)).isOpen).isTrue()
        assertThat(mineSweeperGame.board.squares.get(Position(1, 2)).isOpen).isTrue()
        assertThat(mineSweeperGame.board.squares.get(Position(1, 1)).isOpen).isTrue()
    }
}