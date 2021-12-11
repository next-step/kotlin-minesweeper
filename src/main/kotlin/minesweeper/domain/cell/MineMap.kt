package minesweeper.domain.cell

import minesweeper.domain.board.MineCount
import minesweeper.domain.position.Position
import minesweeper.domain.position.Positions
import minesweeper.domain.position.count
import minesweeper.domain.position.slice

class MineMap(private val mineMap: Map<Position, Boolean>) {

    fun getCells(): Map<Position, Cell> {
        return mineMap.mapValues { (position, isMine) ->
            getCell(position, isMine)
        }
    }

    private fun getCell(position: Position, isMine: Boolean): Cell {
        val aroundMineCount = position.around().count(::isMine)
        return CellFactory.getCell(isMine, MineCount(aroundMineCount))
    }

    private fun isMine(position: Position) = (mineMap[position] == true)
}
