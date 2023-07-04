package domain.model

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MinesweeperGameTest {
    @Test
    fun `초기 상태는 Ready이다`() {
        val game = MinesweeperGame(GameMap.create(2, 2, 2))
        game.status shouldBe Ready
    }

    @Test
    fun `게임을 시작하면 상태가 Running이 된다`() {
        val game = MinesweeperGame(GameMap.create(2, 2, 2))
        game.status shouldBe Ready

        game.start()
        game.status shouldBe Running
    }

    @Test
    fun `지정한 타일을 열 수 있다`() {
        val point = Point.from(0, 0)
        val numberTile = NumberTile(point)
        val field = listOf(listOf(numberTile))
        val game = MinesweeperGame(GameMap(field))

        numberTile.isOpened shouldBe false
        game.playerTurn(point)
        numberTile.isOpened shouldBe true
    }

    @Test
    fun `지뢰 타일을 열면 상태가 GameOver가 된다`() {
        val point = Point.from(0, 0)
        val field = listOf(listOf(Mine(point)))
        val game = MinesweeperGame(GameMap(field))
        game.status shouldBe Ready

        game.start()
        game.status shouldBe Running

        game.playerTurn(point)
        game.status shouldBe GameOver
    }

    @Test
    fun `모든 숫자 타일을 열면 상태가 Win이 된다`() {
        val point = Point.from(0, 0)
        val field = listOf(listOf(NumberTile(point)))
        val game = MinesweeperGame(GameMap(field))
        game.status shouldBe Ready

        game.start()
        game.status shouldBe Running

        game.playerTurn(point)
        game.status shouldBe Win
    }
}
