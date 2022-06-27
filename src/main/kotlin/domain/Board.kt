package domain

class Board(
    private val matrix: Matrix,
) {
    val dimension: Dimension = matrix.dimension
    val cells: Map<Location, Cell> = matrix.cells
}
