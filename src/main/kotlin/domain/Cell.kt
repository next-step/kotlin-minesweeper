package domain

data class Cell(val position: Position, val cellType: CellType) {
    fun isMine(): Boolean {
        return cellType == CellType.MINE
    }
}
