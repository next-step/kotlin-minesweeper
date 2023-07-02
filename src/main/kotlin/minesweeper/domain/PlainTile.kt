package minesweeper.domain

class PlainTile : Tile(TileType.PLAIN) {
    var nearMineCount = DEFAULT_COUNT
        private set

    fun increaseNearMineCount() {
        nearMineCount = nearMineCount.inc()
    }

    companion object {
        const val DEFAULT_COUNT = 0
    }
}
