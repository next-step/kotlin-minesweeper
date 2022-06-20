package domain

import domain.strategy.MineAllocationStrategy

class Minesweeper(minesweeperProperty: MinesweeperProperty, mineAllocationStrategy: MineAllocationStrategy) {

    private val _board: Array<Array<Place>> =
        Array(minesweeperProperty.height) { Array(minesweeperProperty.width) { Place(PlaceType.NOT_MINE) } }

    val board: Array<Array<Place>>
        get() {
            return _board
        }

    init {
        val assignedMineLocation = mineAllocationStrategy.calculate(
            totalPlaceNumber = minesweeperProperty.width * minesweeperProperty.height,
            numberToAllocate = minesweeperProperty.mineCount
        )

        assignedMineLocation.map {
            val row = it / minesweeperProperty.height
            val col = it % minesweeperProperty.width
            _board[row][col].placeType = PlaceType.MINE
        }
    }
}
