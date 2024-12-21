package mine.domain

import mine.enums.MineCell

data class MineRow(val mineCells: List<MineCell>) {
    fun isValidCell(col: Int): Boolean {
        return col in this.mineCells.indices
    }
}
