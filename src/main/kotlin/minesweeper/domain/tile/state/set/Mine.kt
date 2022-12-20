package minesweeper.domain.tile.state.set

import minesweeper.domain.tile.Symbol
import minesweeper.domain.tile.pos.Coordinate
import minesweeper.domain.tile.state.Opened

data class Mine(override val coordinate: Coordinate) : Opened() {
    override val isMine: Boolean = true

    override val symbol: Symbol = Symbol.MINE
}
