package minesweeper.domain.tile.state

import minesweeper.domain.tile.Marking
import minesweeper.domain.tile.pos.Coordinate

sealed interface Tile {
    val coordinate: Coordinate

    val isMine: Boolean

    val isChecked: Boolean

    val marking: Marking
}
