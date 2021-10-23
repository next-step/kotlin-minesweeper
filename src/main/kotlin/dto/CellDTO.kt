package dto

import model.CellType
import model.Position
import model.State

class CellDTO(
    val cellType: CellType,
    val position: Position,
    val state: State
)