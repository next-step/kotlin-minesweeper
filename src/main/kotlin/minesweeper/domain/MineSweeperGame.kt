package minesweeper.domain

import minesweeper.domain.board.CellBoard
import minesweeper.domain.board.GameBoard
import minesweeper.domain.vo.PositiveNumber

class MineSweeperGame {
    private val minesGenerator: RandomMinesGenerator = RandomMinesGenerator

    fun createBoard(height: PositiveNumber, width: PositiveNumber, mineCount: PositiveNumber): GameBoard {
        val randomMines = minesGenerator.generate(
            totalCount = height * width,
            mineCount = mineCount
        )
        val cellBoard = CellBoard.generate(width, randomMines)
        return GameBoard.from(cellBoard)
    }
}
