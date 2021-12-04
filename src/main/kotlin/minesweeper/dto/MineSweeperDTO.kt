package minesweeper.dto

import minesweeper.domain.cell.Cells

class MineSweeperDTO(val rows: List<String>) {

    companion object {
        fun of(cells: Cells): MineSweeperDTO {
            val value = cells.groupBy { it.position().y }.map {
                it.value.joinToString(BLACK) { cell ->
                    if (cell.isHiddenCell()) {
                        return@joinToString IS_HIDDEN
                    }

                    when (cell.isNotMineCell()) {
                        false -> MINE_ICON
                        true -> cell.getCellAdjacentCount().toString()
                    }
                }
            }
            return MineSweeperDTO(value)
        }

        private const val IS_HIDDEN = "C"
        private const val BLACK = " "
        private const val MINE_ICON = "*"
    }
}
