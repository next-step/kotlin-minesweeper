package domain

import domain.geometric.Dimension

class Board(
    private val matrix: Matrix,
) {
    val dimension: Dimension = matrix.dimension
    val cells: List<Cell> = matrix.cells
}
