package domain

import enum.CellStatus

data class Cell(val position: Position, var status: CellStatus = CellStatus.EMPTY)
