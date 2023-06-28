package minesweeper.domain

class MineMap(private val width: Width, private val height: Height) {

    val mineMap = List(width.getWidth()) {
        TileColumn(List(height.getHeight()) { Tile(TileType.PLAIN) })
    }

    fun makeMine(mineCount: MineCount) {
        require(width.getWidth() * height.getHeight() >= mineCount.getMineCount()) {
            "지뢰는 넓이*너비의 수보다 작거나 같아야한다"
        }
        var makeCount = 0
        do {
            makeCount = setMineToTile(makeCount)
        } while (makeCount != mineCount.getMineCount())
    }

    private fun setMineToTile(makeCount: Int): Int {
        val rangeX = width.getWidth()
        val rangeY = height.getHeight()
        val position = RandomPosition.of(rangeX, rangeY)
        val positionX = position.first
        val positionY = position.second
        val tile = mineMap[positionX][positionY]
        if (!tile.isMine()) {
            tile.setMine()
        }
        return makeCount.inc()
    }
}
