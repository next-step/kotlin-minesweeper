package domain

import domain.strategy.MineAllocationStrategy

class Minesweeper(minesweeperProperty: MinesweeperProperty, mineAllocationStrategy: MineAllocationStrategy) {

    private val _board: Array<CharArray> =
        Array(minesweeperProperty.height) { CharArray(minesweeperProperty.width) { PLACE_NOT_MINE } }

    val board: Array<CharArray>
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
            _board[row][col] = PLACE_MINE
        }
    }

    companion object {
        private const val PLACE_MINE = '*'
        private const val PLACE_NOT_MINE = 'B'
    }
}
