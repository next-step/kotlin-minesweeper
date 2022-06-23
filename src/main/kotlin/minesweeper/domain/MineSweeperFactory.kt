package minesweeper.domain

data class MineSweeperFactory(val mineCoordinateStrategy: MineCoordinateStrategy = RandomMineCoordinateStrategy) {

    fun mineSweeper(height: Int, width: Int, mineCount: Int): MineSweeper {
        val coordinates = Coordinate.listOf(height, width)
        val mineCoordinates = mineCoordinateStrategy.mineCoordinates(coordinates, mineCount)
        val boardFields = coordinates.map { boardField(mineCoordinates, it) }
        return MineSweeper(BoardFields(boardFields))
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
