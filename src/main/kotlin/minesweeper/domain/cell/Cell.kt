package minesweeper.domain.cell

interface Cell {
    fun location(): Location

    fun symbol(): Symbol

    fun withIncrementedNumberOfAdjacentMines(): Cell
}
