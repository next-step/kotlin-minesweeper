package minesweeper.domain

sealed class Cell {
    object None : Cell()
    object Mine : Cell()
}
