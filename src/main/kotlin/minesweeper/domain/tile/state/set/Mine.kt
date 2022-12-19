package minesweeper.domain.tile.state.set

import minesweeper.domain.tile.Symbol
import minesweeper.domain.tile.pos.Coordinate
import minesweeper.domain.tile.state.Opened

class Mine(coordinate: Coordinate) : Opened(coordinate) {
    override val isMine: Boolean = true

    override val symbol: Symbol = Symbol.MINE
}
