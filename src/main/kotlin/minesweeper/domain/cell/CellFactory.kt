package minesweeper.domain.cell

import minesweeper.domain.board.MineCount
import minesweeper.domain.position.Positions
import minesweeper.domain.position.slice

object CellFactory {

    fun getCell(isMine: Boolean, aroundMineCount: MineCount): Cell {
        return when {
            isMine -> MineCell()
            else -> BlockCell(aroundMineCount)
        }
    }

    fun getCells(positions: Positions, mineIndexes: List<Int>): Cells {
        val minePositions = positions.slice(mineIndexes)
        val mineMap = positions.mapToIsIn(minePositions)
        val cells = MineMap(mineMap).getCells()
        return Cells(cells)
    }
}
