package minesweeper.domain

class MinesMap(private val columnSize: NumberValue, private val rowSize: NumberValue) {

    val columns = List(columnSize.value) { MineRow(rowSize) }

    operator fun List<MineRow>.get(position: MinePosition): Tile {
        return this[position.x][position.y]
    }

    operator fun List<MineRow>.get(position: Position): MineRow {
        return this[position.value]
    }

    fun get(position: MinePosition): Tile {
        return columns[position]
    }

    fun setMine(position: MinePosition): Boolean {
        val mine: Tile = columns[position]
        if (mine.isMine) {
            return false
        }
        mine.setMine()
        increaseMineCountNearTiles(position)
        return true
    }

    private fun increaseMineCountNearTiles(position: MinePosition) {

        val rowRange = getRange(position.y, rowSize)
        getRange(position.x, columnSize).forEach {
            columns[it].increaseMineCountNearTiles(rowRange)
        }
    }

    fun isFinishGame(): Boolean {
        return columns.none {
            it.sizeOfRemainTiles() != SIZE_ZERO
        }
    }

    fun openNearTiles(position: MinePosition) {
        val tile = columns[position]
        if (tile.isMine || tile.isOpen) {
            return
        }

        tile.open()

        if (tile.nearMineCount != SIZE_ZERO) {
            return
        }

        val columnRange = getRange(position.x, columnSize)
        val rowRange = getRange(position.y, rowSize)
        columnRange.forEach { columnPosition ->
            val rows = columns[columnPosition]
            rows.getTiles(rowRange) { rowIndex ->
                openNearTiles(MinePosition.of(columnPosition, rowIndex))
            }
        }
    }

    private fun getRange(position: Position, size: NumberValue): List<Int> {
        return (position.dec()..position.inc())
            .filter { it in MINIMUM_POSITION until size.value }
    }

    companion object {
        const val SIZE_ZERO = 0
        const val MINIMUM_POSITION = 0
    }
}
