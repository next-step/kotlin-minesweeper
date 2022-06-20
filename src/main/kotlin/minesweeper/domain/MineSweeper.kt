package minesweeper.domain

data class MineSweeper(val mineCoordinateStrategy: MineCoordinateStrategy = RandomMineCoordinateStrategy) {

    fun mineBoard(height: Int, width: Int, mineCount: Int): MineBoard {
        val coordinates = Coordinate.listOf(height, width)
        val mineCoordinates = mineCoordinateStrategy.mineCoordinates(coordinates, mineCount)
        val boardFields = coordinates.map { BoardField(it, mineCoordinates.contains(it)) }
        return MineBoard(boardFields)
    }
}
