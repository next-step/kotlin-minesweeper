package minesweeper.domain.board

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.CellMark

data class MineBoard(
    val cells: Set<Cell>,
) {

    companion object {
        fun from(positions: Positions): MineBoard {
            val adjacentMineCountByPositions = positions.adjacentMineCountByPosition
            val cells = adjacentMineCountByPositions.map { (position, mineCount) ->
                if (positions.isMine(position)) Cell(position, CellMark.MINE)
                else Cell(position, CellMark.from(mineCount))
            }.toSet()
            return MineBoard(cells)
        }
    }
}
