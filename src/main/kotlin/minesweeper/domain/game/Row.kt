package minesweeper.domain.game

data class Row(private val cells: List<Cell>) : List<Cell> by cells
