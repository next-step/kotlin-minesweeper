package minesweeper.model

import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.matchers.types.shouldBeSameInstanceAs
import org.junit.jupiter.api.Test

class MineSweeperGameTest {
    @Test
    fun `지뢰찾기 게임의 너비와 높이, 지뢰 갯수를 받아 게임을 생성한다`() {
        // given
        val cols = 10
        val rows = 10
        val mines = 10

        // when
        val mineSweeperGame = MineSweeperGame(cols = cols, rows = rows, mines = mines)

        // then
        mineSweeperGame.shouldBeInstanceOf<MineSweeperGame>()
        mineSweeperGame.minefield.size shouldBeSameInstanceAs cols
    }
}
