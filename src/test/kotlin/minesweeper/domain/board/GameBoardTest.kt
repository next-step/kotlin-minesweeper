package minesweeper.domain.board

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.test.TestObjectGenerator
import minesweeper.domain.vo.Position

class GameBoardTest : StringSpec({
    "CellBoard를 전달하면 GameBoard를 생성할 수 있다." {
        val minePositions = listOf(Position.of(x = 0, y = 0))
        val cellBoard = TestObjectGenerator.cellBoard(minePositions, 2, 2)

        val gameBoard = GameBoard.from(cellBoard)

        gameBoard.board[0][0].getNeighborMineCount() shouldBe 0
        gameBoard.board[0][1].getNeighborMineCount() shouldBe 1
        gameBoard.board[1][0].getNeighborMineCount() shouldBe 1
        gameBoard.board[1][1].getNeighborMineCount() shouldBe 1
    }
})
