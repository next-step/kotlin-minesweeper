package minesweeper.domain

data class MineSweeper(val mineCoordinateStrategy: MineCoordinateStrategy = RandomMineCoordinateStrategy) {

    fun mineBoard(height: Int, width: Int, mineCount: Int): MineBoard {
        val coordinates = Coordinate.listOf(height, width)
        val mineCoordinates = mineCoordinateStrategy.mineCoordinates(coordinates, mineCount)
        val boardFields = coordinates.map { boardField(mineCoordinates, it) }
        return MineBoard(BoardFields(boardFields))
    }

    private fun boardField(
        mineCoordinates: List<Coordinate>,
        it: Coordinate
    ): BoardField {
        return if (mineCoordinates.contains(it)) {
            BoardField.mine(it)
        } else {
            BoardField.nonMine(it)
        }
    }
}
