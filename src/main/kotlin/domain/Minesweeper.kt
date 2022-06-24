package domain

import domain.strategy.MineAllocationStrategy

class Minesweeper(minesweeperProperty: MinesweeperProperty, mineAllocationStrategy: MineAllocationStrategy) {

    val board: Array<Array<Place>> =
        Array(minesweeperProperty.height) { row ->
            Array(minesweeperProperty.width) { col ->
                val number = (row * minesweeperProperty.width) + col
                Place(number, PlaceType.NOT_MINE)
            }
        }

    init {
        val totalPlaceNumber = minesweeperProperty.width * minesweeperProperty.height
        val numberToAllocate = minesweeperProperty.mineCount

        mineAllocationStrategy.getAssignMineLocation(totalPlaceNumber, numberToAllocate)
            .map { it.number }
            .forEach {
                val row = it / minesweeperProperty.height
                val col = it % minesweeperProperty.width
                board[row][col] = Place(it, PlaceType.MINE)
            }
    }
}
