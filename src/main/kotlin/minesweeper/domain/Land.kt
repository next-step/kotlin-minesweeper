package minesweeper.domain

import minesweeper.domain.tile.Tiles
import minesweeper.domain.tile.pos.Position

data class Land(private val width: Position, private val height: Position, private val tiles: Tiles) {
    fun getTiles(): List<List<String>> {
        val tiles = tiles.getListAsString()
        val land = mutableListOf<List<String>>()
        var fromIndex = 0
        var toIndex = width.value
        for (i in 0..height.value) {
            land.add(tiles.subList(fromIndex, toIndex + CORRECTION_VALUE))
            fromIndex += width.getCalibratedPosition()
            toIndex += width.getCalibratedPosition()
        }
        return land
    }

    companion object {
        private const val CORRECTION_VALUE = 1

        fun of(width: Int, height: Int, tile: Tiles): Land {
            return Land(Position(width), Position(height), tile)
        }
    }
}
