package minesweeper.domain

sealed interface Board {
    val cells: Map<Coordinate, Cell>
}
