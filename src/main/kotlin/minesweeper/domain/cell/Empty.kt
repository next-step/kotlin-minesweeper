package minesweeper.domain.cell

class Empty(
    position: Position,
    private var _numberOfNearbyMines: Int = 0
) : Cell(position) {

    val numberOfNearbyMines get() = _numberOfNearbyMines

    fun accNumberOfNearbyMines() {
        _numberOfNearbyMines += 1
    }
}
