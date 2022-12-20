package minesweeper.domain.tile.state

import minesweeper.domain.tile.Marking
import minesweeper.domain.tile.Tile

abstract class Closed : Tile {
    override val isChecked: Boolean = false

    override val marking: Marking = Marking.CLOSED
}
