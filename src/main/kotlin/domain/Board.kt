package domain

class Board(
    matrix: Matrix,
) {
    val dimension: Dimension = matrix.dimension
    val cells: Map<Location, Cell> = matrix.cells
}
