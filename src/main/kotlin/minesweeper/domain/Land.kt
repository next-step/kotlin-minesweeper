package minesweeper.domain

import minesweeper.domain.tile.Marking
import minesweeper.domain.tile.Tiles
import minesweeper.domain.tile.pos.Position

data class Land(private val width: Position, private val tiles: Tiles) {
    fun getTiles(): List<Marking> {
        return tiles.getList()
    }

    fun getWidth() = width.getCalibratedPosition()

    companion object {
        fun of(width: Int, tile: Tiles): Land {
            return Land(Position(width), tile)
        }
    }
}
