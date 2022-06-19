package minesweeper.domain

class MineSweeper {

    fun mineBoard(height: Int, width: Int, mineCount: Int): MineBoard {
        val coordinates = Coordinate.listOf(height, width)
        val mineCoordinates = mineCoordinates(coordinates, mineCount)
        val boardFields = coordinates.map { BoardField(it, mineCoordinates.contains(it)) }
        return MineBoard(boardFields)
    }

    private fun mineCoordinates(coordinates: List<Coordinate>, mineCount: Int): List<Coordinate> {
        return coordinates.shuffled().take(mineCount)
    }
}
