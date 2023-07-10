package minesweeper2.domain

abstract class Tile {
    abstract fun getType(): TileType

    fun isMine(): Boolean {
        return getType() == TileType.MINE
    }

    var isCheckedTile = false
}
