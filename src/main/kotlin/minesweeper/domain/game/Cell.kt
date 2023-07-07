package minesweeper.domain.game

data class Cell(val type: CellType, var count: Int = 0) {
    companion object {
        fun of(isMine: Boolean, count: Int): Cell = if (isMine) Cell(CellType.MINE) else Cell(CellType.NORMAL, count)
    }
}
