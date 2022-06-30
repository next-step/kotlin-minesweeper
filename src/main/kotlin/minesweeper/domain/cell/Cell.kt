package minesweeper.domain.cell

sealed class Cell(val position: Position) {

    class Mine(position: Position) : Cell(position)

    class Empty(position: Position, numberOfNearbyMines: Int = 0) : Cell(position) {
        var numberOfNearbyMines: Int = numberOfNearbyMines
            private set

        fun accNumberOfNearbyMines() {
            numberOfNearbyMines += 1
        }
    }

    private var state: CellStatus = CellStatus.CLOSE

    fun open(): Cell {
        check(state != CellStatus.OPEN) { "cell (${position.x}, ${position.y}) was already opened." }
        state = CellStatus.OPEN
        return this
    }

    fun isClosed() = this.state == CellStatus.CLOSE

    fun isOpen() = this.state == CellStatus.OPEN
}
