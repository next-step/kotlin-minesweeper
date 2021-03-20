package minesweeper.domain

class Cells(val cells: List<Cell>, val width: Int) : List<Cell> by cells
