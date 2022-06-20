package domain

import domain.strategy.MineAllocationStrategy

class Minesweeper(minesweeperProperty: MinesweeperProperty, mineAllocationStrategy: MineAllocationStrategy) {

    val board: Array<Array<Place>> =
        Array(minesweeperProperty.height) { Array(minesweeperProperty.width) { Place(PlaceType.NOT_MINE) } }

    init {
        val assignedMineLocation = mineAllocationStrategy.calculate(
            totalPlaceNumber = minesweeperProperty.width * minesweeperProperty.height,
            numberToAllocate = minesweeperProperty.mineCount
        )

        assignedMineLocation.map {
            val row = it / minesweeperProperty.height
            val col = it % minesweeperProperty.width
            board[row][col].placeType = PlaceType.MINE
        }
    }
}
