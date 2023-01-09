package minesweeper.domain.land

import minesweeper.domain.land.state.Area
import minesweeper.domain.tile.Marking
import minesweeper.domain.tile.Marking.Companion.toMarking
import minesweeper.domain.tile.SurroundingTiles
import minesweeper.domain.tile.Tiles
import minesweeper.domain.tile.pos.Coordinate

data class Land(private val area: Area, private var _tiles: Tiles) {
    val tiles: List<Marking>
        get() = _tiles.getList()

    fun getWidth() = area.width

    fun getMineCount(coordinate: Coordinate): Int {
        return SurroundingTiles.values().count {
            coordinate.getSurroundTilesCoordinate(it)?.let(::isMine) ?: false
        }
    }

    fun selectTile(coordinate: Coordinate): Boolean {
        if (isMine(coordinate)) {
            return false
        }
        checkSurroundingTiles(coordinate)
        return true
    }

    private fun checkSurroundingTiles(coordinate: Coordinate) {
        if (_tiles.isChecked(coordinate) || !coordinate.isInArea(area)) {
            return
        }

        val mineCount = getMineCount(coordinate)
        _tiles = _tiles.checkTile(coordinate, toMarking(mineCount))
        if (toMarking(mineCount) != Marking.EMPTY) {
            return
        }

        for (surroundingTiles in SurroundingTiles.values()) {
            coordinate.getSurroundTilesCoordinate(surroundingTiles)?.let(::checkSurroundingTiles)
        }
    }

    private fun isMine(coordinate: Coordinate): Boolean {
        return _tiles.isMine(Coordinate(coordinate.positionX, coordinate.positionY))
    }

    fun isAllOpened(): Boolean {
        return _tiles.isAllOpened()
    }

    companion object {
        fun of(width: Int, height: Int, tile: Tiles): Land {
            return Land(Area(width, height), tile)
        }
    }
}
