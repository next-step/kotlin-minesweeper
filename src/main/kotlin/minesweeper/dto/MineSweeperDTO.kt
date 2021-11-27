package minesweeper.dto

import minesweeper.domain.cell.CellType
import minesweeper.domain.cell.Cells

class MineSweeperDTO(val rows: List<String>) {

    companion object {
        fun of(cells: Cells): MineSweeperDTO {
            val value = cells.groupBy { it.position.y }.map {
                it.value.joinToString(BLACK) { cell ->
                    when (cell.state.cellType) {
                        CellType.IS_MINE -> MINE_ICON
                        CellType.NOT_MINE -> cell.state.getValue().toString()
                    }
                }
            }
            return MineSweeperDTO(value)
        }

        private const val BLACK = " "
        private const val MINE_ICON = "*"
    }
}
