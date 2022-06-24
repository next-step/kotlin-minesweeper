package domain

import domain.strategy.MineAllocationStrategy

class Minesweeper(minesweeperProperty: MinesweeperProperty, mineAllocationStrategy: MineAllocationStrategy) {

    private val _board: MutableList<MutableList<Place>> =
        MutableList(minesweeperProperty.height) { row ->
            MutableList(minesweeperProperty.width) { col ->
                val number = (row * minesweeperProperty.width) + col
                Place(number, PlaceType.NOT_MINE)
            }
        }

    val board: List<List<Place>>
    get() = _board

    init {
        val totalPlaceNumber = minesweeperProperty.width * minesweeperProperty.height
        val numberToAllocate = minesweeperProperty.mineCount

        mineAllocationStrategy.getAssignMineLocation(totalPlaceNumber, numberToAllocate)
            .map { it.number }
            .forEach {
                val row = it / minesweeperProperty.height
                val col = it % minesweeperProperty.width
                _board[row][col] = Place(it, PlaceType.MINE)
            }
    }
}
