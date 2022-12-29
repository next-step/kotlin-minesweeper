package minesweeper.domain.tile.state.set

import minesweeper.domain.tile.pos.Coordinate
import minesweeper.domain.tile.state.Closed

data class NotChecked(override val coordinate: Coordinate, override val isMine: Boolean) : Closed()
