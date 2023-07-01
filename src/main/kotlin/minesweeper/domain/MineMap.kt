package minesweeper.domain

class MineMap(private val width: Width, private val height: Height) {

    val mineMap = List(width.getWidth()) {
        TileRow(List(height.getHeight()) { Tile(TileType.PLAIN) })
    }

    fun makeMine(mineCount: MineCount) {
        require(width.getWidth() * height.getHeight() > mineCount.getMineCount()) {
            "지뢰는 넓이*너비의 수보다 작아야한다"
        }
        var makeCount = 0
        do {
            makeCount = setMineToTile(makeCount)
        } while (makeCount != mineCount.getMineCount())
    }

    private fun setMineToTile(makeCount: Int): Int {
        val rangeX = width.getWidth()
        val rangeY = height.getHeight()
        val (x, y) = RandomPosition.of(rangeX, rangeY)
        val tile = mineMap[x][y]
        if (!tile.isMine()) {
            tile.setMine()
            return makeCount.inc()
        }
        return makeCount
    }
}
