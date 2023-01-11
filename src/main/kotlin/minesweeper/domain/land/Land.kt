package minesweeper.domain.land

import minesweeper.domain.land.state.Area
import minesweeper.domain.tile.Marking
import minesweeper.domain.tile.Marking.Companion.toMarking
import minesweeper.domain.tile.SurroundingTiles
import minesweeper.domain.tile.Tiles
import minesweeper.domain.tile.pos.Coordinate

data class Land(private val area: Area, private var _tiles: Tiles) {
    val tiles: List<Marking>
        get() = _tiles.list

    val width: Int
        get() = area.width

    val isAllOpened: Boolean
        get() = _tiles.isAllOpened()

    fun getMineCount(coordinate: Coordinate): Int = _tiles.getMineCount(coordinate)

    fun selectTile(coordinate: Coordinate): Boolean {
        if (_tiles.isMine(coordinate)) {
            return false
        }
        checkSurroundingTiles(coordinate)
        return true
    }

    private fun checkSurroundingTiles(coordinate: Coordinate) {
        if (_tiles.isChecked(coordinate) || coordinate.isNotInArea(area)) {
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
}
