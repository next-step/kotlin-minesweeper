package domain

class Cell(
    val type: CellType,
) {
    fun isMine(): Boolean {
        return type == CellType.MINE
    }
}
