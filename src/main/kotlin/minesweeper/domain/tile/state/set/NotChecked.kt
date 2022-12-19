package minesweeper.domain.tile.state.set

import minesweeper.domain.tile.pos.Coordinate
import minesweeper.domain.tile.state.Closed

class NotChecked(coordinate: Coordinate, override val isMine: Boolean) : Closed(coordinate)
