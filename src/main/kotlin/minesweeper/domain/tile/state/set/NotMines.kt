package minesweeper.domain.tile.state.set

import minesweeper.domain.tile.Symbol
import minesweeper.domain.tile.pos.Coordinate
import minesweeper.domain.tile.state.Opened

class NotMines(coordinate: Coordinate) : Opened(coordinate) {
    override val isMine: Boolean = false

    override val symbol: Symbol
        get() = TODO("Not yet implemented")
}
