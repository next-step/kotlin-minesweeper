package minesweeper.domain.dto

import minesweeper.domain.board.Cells

class MineSweeperDTO(val rows: List<String>) {

    companion object {
        fun of(cells: Cells): MineSweeperDTO {
            val value = cells.groupBy { it.position.y }.map {
                it.value.joinToString(BLACK) { cell ->
                    when (cell.cellType.getValue()) {
                        -1 -> IS_MINE
                        else -> IS_NOT_MINE
                    }
                }
            }
            return MineSweeperDTO(value)
        }

        private const val BLACK = " "
        private const val IS_MINE = "*"
        private const val IS_NOT_MINE = "C"
    }
}
