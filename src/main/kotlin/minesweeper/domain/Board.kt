package minesweeper.domain

sealed interface Board {
    val cells: Cells
}
