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
        val assignedMineLocations = mineAllocationStrategy
            .getAssignMineLocation(totalPlaceNumber, numberToAllocate)
            .map { it.number }

        board.flatten()
            .filter { assignedMineLocations.contains(it.number) }
            .forEach { it.placeType = PlaceType.MINE }
    }
}
