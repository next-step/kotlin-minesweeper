package minesweeper.domain.tile.state

import minesweeper.domain.tile.Marking

abstract class Closed : Tile {
    override val isChecked: Boolean = false

    override val marking: Marking = Marking.CLOSED
}
