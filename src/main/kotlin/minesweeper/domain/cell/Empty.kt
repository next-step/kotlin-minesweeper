package minesweeper.domain.cell

class Empty(position: Position, var numberOfNearbyMines: Int = 0) : Cell(position)
