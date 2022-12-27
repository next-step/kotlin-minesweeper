package minesweeper.domain

import minesweeper.domain.tile.Tiles
import minesweeper.domain.tile.pos.Position

data class Land(private val width: Position, private val tiles: Tiles) {
    fun getTiles(): List<String> {
        return tiles.getListAsString()
    }

    companion object {
        fun of(width: Int, tile: Tiles): Land {
            return Land(Position(width), tile)
        }
    }
}
