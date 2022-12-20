package minesweeper.domain.tile.state

import minesweeper.domain.tile.Symbol
import minesweeper.domain.tile.Tile

abstract class Closed : Tile {
    override val isChecked: Boolean = false

    override val symbol: Symbol = Symbol.CLOSED
}
