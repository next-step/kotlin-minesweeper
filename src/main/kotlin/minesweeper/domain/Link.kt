package minesweeper.domain

class Link(private val matrix: Matrix, private val target: List<Cell>) {
    fun cells(index: Int): List<Cell> {
        val indexes = matrix.around(index)
        return target
            .filterIndexed { i, _ -> indexes.contains(i) }
            .map { it }
    }
}
