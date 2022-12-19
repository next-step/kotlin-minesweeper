package minesweeper.domain.tile.state

import minesweeper.domain.tile.Symbol
import minesweeper.domain.tile.Tile
import minesweeper.domain.tile.pos.Coordinate

abstract class Closed(override val coordinate: Coordinate) : Tile {
    override val isChecked: Boolean = false

    override val symbol: Symbol = Symbol.CLOSED
}
