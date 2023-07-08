package minesweeper.domain

import io.kotest.matchers.shouldBe
import minesweeper.domain.pin.MinePin
import org.junit.jupiter.api.Test

class GameBoardTest {

    @Test
    fun `게임보드는 위치를 주입받아서 지뢰를 생성해줄 수 있다`() {
        val board = GameBoard.ready(10, 10)

        board.setMine({ 0 }, { 0 })

        val firstPin = board.getPinAt(0, 0)

        (firstPin is MinePin) shouldBe true
    }
}
