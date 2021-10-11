package model

import model.position.Position

data class CellIdentity(
    private val cellType: CellType,
    private val aroundMineCount: AroundMineCount,
    private val position: Position
)
