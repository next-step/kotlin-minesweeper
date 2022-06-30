package minesweeper.domain.cell

object CellMaker {
    fun make(width: Int, height: Int, numberOfMines: Int): List<Cell> {
        val positions = makePositions(width, height)
        return makeCells(positions, numberOfMines)
    }

    private fun makePositions(width: Int, height: Int): List<Position> {
        val size = width * height
        return List(size) {
            val x = it % width
            val y = it / width
            Position(x, y)
        }.shuffled()
    }

    private fun makeCells(positions: List<Position>, numberOfMines: Int): List<Cell> =
        positions.mapIndexed { index, position ->
            if (index < numberOfMines) {
                Mine(position)
            } else {
                Empty(position)
            }
        }.sortedWith(compareBy({ it.position.x }, { it.position.y }))
}
