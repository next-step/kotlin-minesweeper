package minesweeper.domain

data class Tile(private var type: TileType) {

    fun isMine() : Boolean {
        return type == TileType.MINE
    }

    fun setMine() {
        type = TileType.MINE
    }
}
