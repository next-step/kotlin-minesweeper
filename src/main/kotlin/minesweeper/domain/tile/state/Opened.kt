package minesweeper.domain.tile.state

import minesweeper.domain.tile.Tile
import minesweeper.domain.tile.pos.Coordinate

abstract class Opened(override val coordinate: Coordinate) : Tile {
    override val isChecked: Boolean = true
}
