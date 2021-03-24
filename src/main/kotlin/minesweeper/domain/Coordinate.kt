package minesweeper.domain

class Coordinate(private val matrix: Matrix) {
    fun sideIndexes(index: Int): List<Int> {
        return matrix.around(index).sorted()
    }
}
