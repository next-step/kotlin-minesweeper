package minesweeper.domain

class BoardRow(
    var rowInfo: List<Cell>
) {
    fun updateCell(col: Int) {
        rowInfo = rowInfo.toMutableList().apply {
            set(col, MineCell(rowInfo[col].point))
        }
    }
}
