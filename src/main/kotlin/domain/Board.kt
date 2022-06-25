package domain

import domain.geometric.Dimension

class Board(
    val dimension: Dimension,
    val cells: List<Cell>
) {
    constructor(matrix: Matrix) : this(
        matrix.dimension,
        matrix.cells
    )
}
