package minesweeper.domain.cell

import minesweeper.domain.board.MineCount

object CellFactory {

    fun getCell(isMine: Boolean, aroundMineCount: MineCount): Cell {
        return when {
            isMine -> MineCell
            else -> BlockCell(aroundMineCount)
        }
    }
}
