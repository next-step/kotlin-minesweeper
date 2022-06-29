package minesweeper.domain.cell

class Empty(
    position: Position,
    numberOfNearbyMines: Int = 0
) : Cell(position) {

    var numberOfNearbyMines: Int = numberOfNearbyMines
        private set

    fun accNumberOfNearbyMines() {
        numberOfNearbyMines += 1
    }
}
