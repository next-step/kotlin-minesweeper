package minesweeper.domain

abstract class Tile(private val type: TileType) {

    fun isMine(): Boolean {
        return type == TileType.MINE
    }
}
