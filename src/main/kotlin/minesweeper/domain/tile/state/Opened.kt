package minesweeper.domain.tile.state

import minesweeper.domain.tile.Tile

abstract class Opened : Tile {
    override val isChecked: Boolean = true
}
