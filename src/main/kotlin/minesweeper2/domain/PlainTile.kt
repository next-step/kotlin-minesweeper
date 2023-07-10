package minesweeper2.domain

open class PlainTile : Tile() {
    var nearMineCount = DEFAULT_COUNT
        private set

    fun increaseNearMineCount() {
        nearMineCount = nearMineCount.inc()
    }

    fun isEmptyTile(): Boolean {
        return nearMineCount == DEFAULT_COUNT
    }

    override fun getType(): TileType {
        return TileType.PLAIN
    }

    companion object {
        const val DEFAULT_COUNT = 0
    }
}
