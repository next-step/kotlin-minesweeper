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

        calculateNearMineCount(minesweeperProperty)
    }

    private fun calculateNearMineCount(minesweeperProperty: MinesweeperProperty) {
        for (row: Int in 0 until minesweeperProperty.height) {
            for (col: Int in 0 until minesweeperProperty.width) {
                val place = _board[row][col]
                if (place.isNotMine()) place.nearMineCount = getNearMineCount(row, col)
            }
        }
    }

    private fun getNearMineCount(row: Int, col: Int): Int {
        var count = 0

        if (isMineInPlace(row - 1, col - 1)) count++
        if (isMineInPlace(row - 1, col)) count++
        if (isMineInPlace(row - 1, col + 1)) count++
        if (isMineInPlace(row, col - 1)) count++
        if (isMineInPlace(row, col + 1)) count++
        if (isMineInPlace(row + 1, col - 1)) count++
        if (isMineInPlace(row + 1, col)) count++
        if (isMineInPlace(row + 1, col + 1)) count++

        return count
    }

    private fun isMineInPlace(row: Int, col: Int): Boolean {
        return getPlaceOrNull(row, col)?.isMine() ?: false
    }

    private fun getPlaceOrNull(row: Int, col: Int): Place? {
        return try {
            _board[row][col]
        } catch (e: IndexOutOfBoundsException) {
            null
        }
    }
}
