package domain.vo

import domain.enums.CellType

data class Cell(val position: Position, val cellType: CellType = CellType.NOT_MINE)
