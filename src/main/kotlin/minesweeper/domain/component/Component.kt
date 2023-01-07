package minesweeper.domain.component

import minesweeper.domain.NearMineCount
import minesweeper.domain.Position

sealed interface Component {
    val position: Position
    val isMine: Boolean
    val nearMineCount: NearMineCount
}

class DefaultComponent(
    override val position: Position,
    override val isMine: Boolean,
    override val nearMineCount: NearMineCount
) : Component
