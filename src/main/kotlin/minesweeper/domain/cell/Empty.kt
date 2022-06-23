package minesweeper.domain.cell

class Empty(
    position: Position,
    nearbyPositions: Positions,
    private var _numberOfNearbyMines: Int = 0
) : Cell(position, nearbyPositions) {

    val numberOfNearbyMines get() = _numberOfNearbyMines

    fun accNumberOfNearbyMines() {
        _numberOfNearbyMines += 1
    }
}
