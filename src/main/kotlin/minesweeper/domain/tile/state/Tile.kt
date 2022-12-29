package minesweeper.domain.tile.state

import minesweeper.domain.tile.Marking
import minesweeper.domain.tile.pos.Coordinate
import minesweeper.domain.tile.state.set.Mine
import minesweeper.domain.tile.state.set.NotChecked

sealed class Tile {
    abstract val coordinate: Coordinate

    abstract val isMine: Boolean

    abstract val isChecked: Boolean

    abstract val marking: Marking

    companion object {
        fun of(coordinate: Coordinate, isMine: Boolean): Tile {
            return if (isMine) Mine(coordinate) else NotChecked(coordinate, false)
        }
    }
}
