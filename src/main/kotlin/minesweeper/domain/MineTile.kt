package minesweeper.domain

class MineTile : Tile() {
    override fun getType(): TileType {
        return TileType.MINE
    }
}
