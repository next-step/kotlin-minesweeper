package business

class Cell(private val cellStatus: CellStatus) {
    fun isMine(): Boolean = cellStatus.isMine()
}
