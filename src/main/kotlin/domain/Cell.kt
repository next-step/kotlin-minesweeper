package domain

data class Cell(
    private var cellState: CellState = CellState(),
    private var mineNumber: MineNumber = MineNumber()
) {
    fun isOpen(): Boolean = cellState.isOpen()

    fun hasMine(): Boolean = cellState.hasMine()

    fun isBlank(): Boolean = mineNumber.isBlank()

    fun mineNumber(): Int = mineNumber.number

    fun open() {
        cellState = cellState.open()
    }

    fun layMine() {
        cellState = cellState.layMine()
    }

    fun increaseMineNumber() {
        mineNumber++
    }
}
