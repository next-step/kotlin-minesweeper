package minesweeper.domain

private operator fun List<TileRow>.get(position: MinePosition): Tile {
    return this[position.positionX][position.positionY]
}

private operator fun List<TileRow>.get(position: Position): TileRow {
    return this[position.position]
}

private operator fun List<TileRow>.set(position: MinePosition, tile: Tile) {
    this[position.positionX][position.positionY] = tile
}

class MineMap(private val width: Length, private val height: Length) {

    val mineMap = List(height.value) {
        TileRow(MutableList(width.value) { PlainTile() })
    }

    fun makeMine(mineCount: MineCount) {
        require(width.value * height.value > mineCount.count) {
            "지뢰는 넓이*너비의 수보다 작아야한다"
        }
        var makeCount = 0
        do {
            makeCount = setMineToTile(makeCount)
        } while (makeCount != mineCount.count)
    }

    private fun setMineToTile(makeCount: Int): Int {
        val position = RandomPosition.of(height.value, width.value)
        val tile = mineMap[position]
        if (!tile.isMine()) {
            mineMap[position] = MineTile()
            setNearMineMap(position)
            return makeCount.inc()
        }
        return makeCount
    }

    fun isMineTile(position: MinePosition): Boolean {
        return mineMap[position].isMine()
    }

    private fun setNearMineMap(minePosition: MinePosition) {
        val minePositionX = minePosition.positionX
        for (position in minePositionX.dec()..minePositionX.inc()) {
            setNearMineColumn(position, minePosition)
        }
    }

    private fun setNearMineColumn(positionX: Int, minePosition: MinePosition) {
        if (isOutOfMap(positionX, height)) {
            return
        }
        val minePositionY = minePosition.positionY
        for (positionY in minePositionY.dec()..minePositionY.inc()) {
            increaseNearMineRow(Position(positionX), positionY)
        }
    }

    private fun isOutOfMap(position: Int, limit: Length) = position < 0 || position >= limit.value

    private fun increaseNearMineRow(positionX: Position, positionY: Int) {
        if (isOutOfMap(positionY, width)) {
            return
        }
        increaseNearMineCount(mineMap[MinePosition(positionX, Position(positionY))])
    }

    private fun increaseNearMineCount(tile: Tile) {
        if (tile is PlainTile) {
            tile.increaseNearMineCount()
        }
    }

    fun openMine(minePosition: MinePosition, operation: (Boolean, Int) -> Unit) {
        when(val tile = mineMap[minePosition]) {
            is MineTile -> operation(true, 0)
            is PlainTile -> {
                tile.openTile()
                operation(false, tile.nearMineCount)
            }
        }
    }
}
