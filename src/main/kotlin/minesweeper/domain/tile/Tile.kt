package minesweeper.domain.tile

import minesweeper.domain.tile.pos.Coordinate

interface Tile {
    val coordinate: Coordinate

    val isMine: Boolean

    val isChecked: Boolean

    val symbol: Symbol
}
