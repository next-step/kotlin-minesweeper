package minesweeper.domain.cell

import minesweeper.domain.common.Position

class Empty(position: Position, var numberOfNearbyMines: Int = 0) : Cell(position)
