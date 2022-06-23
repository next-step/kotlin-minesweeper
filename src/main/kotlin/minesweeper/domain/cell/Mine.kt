package minesweeper.domain.cell

class Mine(
    position: Position,
    nearbyPositions: Positions
) : Cell(position, nearbyPositions)
