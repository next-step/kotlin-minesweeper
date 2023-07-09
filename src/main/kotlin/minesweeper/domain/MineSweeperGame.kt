package minesweeper.domain

import minesweeper.domain.board.CellBoard
import minesweeper.domain.vo.PositiveNumber

class MineSweeperGame(private val minesGenerator: RandomMinesGenerator) {
    fun createBoard(height: PositiveNumber, width: PositiveNumber, mineCount: PositiveNumber): CellBoard {
        val randomMines = minesGenerator.generate(
            totalCount = height * width,
            mineCount = mineCount
        )
        return CellBoard.generate(width, randomMines)
    }
}
