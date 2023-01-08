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
        return SurroundingTiles.values().count { surroundingTiles ->
            val (x, y) = coordinate.getSurroundTilesCoordinate(surroundingTiles)
            isMine(x, y)
        }
    }

    fun selectTile(coordinate: Coordinate): Boolean {
        val (positionX, positionY) = coordinate.getPositionXY()
        if (isMine(positionX, positionY)) {
            return false
        }
        checkSurroundingTiles(positionX, positionY)
        return true
    }

    private fun checkSurroundingTiles(positionX: Int, positionY: Int) {
        if (isInvalidCoordinate(positionX, positionY)) {
            return
        }

        val coordinate = Coordinate.of(positionX, positionY)
        if (_tiles.isChecked(coordinate)) {
            return
        }

        val mineCount = getMineCount(coordinate)
        _tiles = _tiles.checkTile(coordinate, toMarking(mineCount))
        if (toMarking(mineCount) != Marking.EMPTY) {
            return
        }

        for (surroundingTiles in SurroundingTiles.values()) {
            val (x, y) = coordinate.getSurroundTilesCoordinate(surroundingTiles)
            checkSurroundingTiles(x, y)
        }
    }

    private fun isMine(positionX: Int, positionY: Int): Boolean {
        if (isInvalidCoordinate(positionX, positionY)) {
            return false
        }
        return _tiles.isMine(Coordinate.of(positionX, positionY))
    }

    private fun isInvalidCoordinate(positionX: Int, positionY: Int): Boolean {
        return positionX < ZERO || positionX > area.width || positionY < ZERO || positionY > area.height
    }

    fun isAllOpened(): Boolean {
        return _tiles.isAllOpened()
    }

    companion object {
        private const val ZERO = 0

        fun of(width: Int, height: Int, tile: Tiles): Land {
            return Land(Area(width, height), tile)
        }
    }
}
