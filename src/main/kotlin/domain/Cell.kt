package domain

class Cell {
    private var cellState = CellState()
    private var mineNumber: MineNumber = MineNumber()

    fun isOpen(): Boolean = cellState.isOpen()
    fun isMine(): Boolean = cellState.isMine()
    fun isBlank(): Boolean = mineNumber() == 0
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
