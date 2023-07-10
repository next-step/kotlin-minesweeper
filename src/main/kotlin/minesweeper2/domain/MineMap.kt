package minesweeper2.domain

private operator fun List<TileRow>.get(position: MinePosition): Tile {
    return this[position.positionX][position.positionY]
}

private operator fun List<TileRow>.get(position: Position): TileRow {
    return this[position.position]
}

private operator fun List<TileRow>.set(position: MinePosition, tile: Tile) {
    this[position.positionX][position.positionY] = tile
}

class MineMap(lengths: Pair<Length, Length>, val mineCount: MineCount) {

    private val width: Length = lengths.first
    private val height: Length = lengths.second

    val mineMap = List(height.value) {
        TileRow(MutableList(width.value) { PlainTile() })
    }

    init {
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

    private fun setNearMineMap(position: MinePosition) {
        val columnRange = getRange(position.positionX, height)
        val rowRange = getRange(position.positionY, width)

        for (columnIndex in columnRange) {
            val tileRow = mineMap[columnIndex]
            tileRow.increaseMineCountNearTile(rowRange)
        }
    }

    private fun getRange(position: Position, limit: Length): List<Int> {
        return (position.dec()..position.inc())
            .filter { it in MINIMUM_POSITION until limit.value }
    }

    fun startGame(gameStateNotify: GameStateNotify) {
        openTile(gameStateNotify)
    }

    private fun openTile(gameStateNotify: GameStateNotify) {
        val position = gameStateNotify.getOpenPosition()
        val tile = mineMap[position]
        if (tile.isMine()) {
            gameStateNotify.showGameState(false)
            return
        }
        openTile(position)
        proceedGame(gameStateNotify)
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

    private fun proceedGame(gameStateNotify: GameStateNotify) {
        gameStateNotify.showMineMapInProgress(mineMap)
        val sizeOfUncheckedTiles = mineMap.sumOf {
            it.sizeOfUnChecked()
        }
        if (mineCount.count == sizeOfUncheckedTiles) {
            gameStateNotify.showGameState(true)
            return
        }
        openTile(gameStateNotify)
    }

    companion object {
        const val MINIMUM_POSITION = 0
    }
}
