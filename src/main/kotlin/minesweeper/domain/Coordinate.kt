package minesweeper.domain

class Coordinate(private val index: Int, private val matrix: Matrix) {
    val sideIndexes: List<Int>
        get() {
            return matrix.around(index).sorted()
        }
}
