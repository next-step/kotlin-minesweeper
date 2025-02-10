package mine.domain

import mine.enums.MineCell
import mine.enums.MineCell.MINE

data class MineRow(val mineCells: List<MineCell>) {
    fun areAllNonMineCellsOpen(): Boolean {
        return mineCells.all {
            when (it) {
                is MINE -> !it.isOpen
                is MineCell.Number -> it.isOpen
            }
        }
    }
}
