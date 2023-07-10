package minesweeper.domain

import minesweeper2.domain.MineMap



//private operator fun List<MineRow>.set(position: MinePosition, tile: Tile) {
//    this[position.x][position.y] = tile
//}

class MineColumn(private val columnSize: Number, private val rowSize: Number) {

    val columns = List(columnSize.value) { MineRow(rowSize) }

    fun setMine(position: MinePosition): Boolean {
        val mine: Tile = columns[position]
        if(mine.isMine) {
            return false
        }
        mine.setMine()
        increaseMineCountNearTiles(position)
        return true
    }

    private fun increaseMineCountNearTiles(position: MinePosition) {

        val rangeRow = getRange(position.y, rowSize)
        getRange(position.x, columnSize).forEach {
            columns[it].increateMineCountNearTiles(rangeRow)
        }
    }

    private fun getRange(position: Position, size: Number): List<Int> {
        return (position.dec()..position.inc())
            .filter { it in MineMap.MINIMUM_POSITION until size.value }
    }

    operator fun List<MineRow>.get(position: MinePosition): Tile {
        return this[position.x][position.y]
    }

    operator fun List<MineRow>.get(position: Position): MineRow {
        return this[position.value]
    }

    fun get(position: MinePosition): Tile {
        return columns[position]
    }
}
