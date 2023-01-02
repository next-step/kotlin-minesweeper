package minesweeper.domain.component

import minesweeper.domain.Position

sealed interface Component {
    val position: Position
    val isMine: Boolean
}

class DefaultComponent(override val position: Position, override val isMine: Boolean) : Component
