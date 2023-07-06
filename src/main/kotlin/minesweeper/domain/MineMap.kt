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

    var mineTitleCount: MineCount? = null

    fun makeMine(mineCount: MineCount) {
        require(width.value * height.value > mineCount.count) {
            "지뢰는 넓이*너비의 수보다 작아야한다"
        }
        mineTitleCount = mineCount
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

    private fun setNearMineMap(position: MinePosition) {
        val columnRange = getRange(position.positionX, height)
        val rowRange = getRange(position.positionY, width)

        for (columnIndex in columnRange) {
            val tileRow = mineMap[columnIndex]
            tileRow.inCreaseMineCountNearTile(rowRange)
        }
    }

    fun openTile(position: MinePosition, loose: (Boolean) -> Unit) {
        val tile = mineMap[position]
        if (tile is MineTile) {
            loose(true)
            return
        }
        openTile(position)
        loose(false)
    }

    private fun openTile(position: MinePosition) {
        val tile = mineMap[position]
        if (tile !is PlainTile) {
            return
        }

        if (tile.isCheckedTile) {
            return
        }

        tile.isCheckedTile = true

        if (!tile.isEmptyTile()) {
            return
        }

        val columnRange = getRange(position.positionX, height)
        val rowRange = getRange(position.positionY, width)

        columnRange.forEach { columnPosition ->
            val tileRow = mineMap[columnPosition]
            tileRow.getTiles(rowRange) { rowIndex ->
                openTile(MinePosition(Position(columnPosition), Position(rowIndex)))
            }
        }
    }

    private fun getRange(position: Position, limit: Length): List<Int> {
        return (position.dec()..position.inc())
            .filter { it in MINIMUM_POSITION until limit.value }
    }

    companion object {
        const val MINIMUM_POSITION = 0
    }
}
