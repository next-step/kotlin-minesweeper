package minesweeper.domain.tile.state.set

import minesweeper.domain.tile.Marking
import minesweeper.domain.tile.pos.Coordinate
import minesweeper.domain.tile.state.Opened

data class NotMines(override val coordinate: Coordinate) : Opened() {
    override val isMine: Boolean = false

    override val marking: Marking
        get() = TODO("Not yet implemented")
}
