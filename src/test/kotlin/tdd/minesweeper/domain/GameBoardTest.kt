package tdd.minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class GameBoardTest {

    @Test
    fun `높이와 너비, 지뢰 개수를 입력받아 게임판을 생성한다`() {
        assertDoesNotThrow { GameBoard(3, 3, 2) }
    }

    @Test
    fun `입력받은 지뢰 개수만큼 지뢰를 심는다`() {
        val gameBoard = GameBoard(3, 3, 2)
        gameBoard.getRemainingMineCount() shouldBe 2
    }
}
