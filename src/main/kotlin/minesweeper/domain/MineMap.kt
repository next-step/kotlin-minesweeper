package minesweeper.domain

class MineMap(private val width: Width, private val height: Height) {

    val mineMap = List(height.height) {
        TileRow(MutableList(width.width) { PlainTile() })
    }

    fun makeMine(mineCount: MineCount) {
        require(width.width * height.height > mineCount.count) {
            "지뢰는 넓이*너비의 수보다 작아야한다"
        }
        var makeCount = 0
        do {
            makeCount = setMineToTile(makeCount)
        } while (makeCount != mineCount.count)
    }

    private fun setMineToTile(makeCount: Int): Int {
        val position = RandomPosition.of(height.height, width.width)
        val randomHeight = position.randomHeight
        val randomWidth = position.randomWidth
        val positionX = randomHeight.position
        val positionY = randomWidth.position
        val tile = mineMap[positionX][positionY]
        if (!tile.isMine()) {
            mineMap[positionX][positionY] = MineTile()
            checkMine(positionX, positionY)
            return makeCount.inc()
        }
        return makeCount
    }

    private fun checkMine(x: Int, y: Int) {
        for (column in x.dec()..x.inc()) {
            checkMineRow(column, y)
        }
    }

    private fun checkMineRow(column: Int, y: Int) {
        if (column < 0 || column >= mineMap.size) {
            return
        }
        for (row in y.dec()..y.inc()) {
            setMineCount(column, row)
        }
    }

    private fun setMineCount(column: Int, row: Int) {
        if (row < 0 || row >= mineMap[column].size) {
            return
        }
        checkNearMineCount(mineMap[column][row])
    }

    private fun checkNearMineCount(tile: Tile) {
        when (tile) {
            is MineTile -> return
            is PlainTile -> tile.fineNearMine()
        }
    }
}
