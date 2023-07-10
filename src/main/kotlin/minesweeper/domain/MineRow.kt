package minesweeper.domain

class MineRow(width: NumberValue) {

    val rows = List(width.value) { Tile() }

    val size: Int = rows.size
    operator fun get(position: Position): Tile {
        return rows[position.value]
    }

    operator fun get(position: Int): Tile {
        return rows[position]
    }

    fun increateMineCountNearTiles(rangeRow: List<Int>) {
        rangeRow.forEach {
            rows[it].increaseNearMineCount()
        }
    }

    fun sizeOfRemainTiles(): Int {
        return rows.filterNot {
            it.isOpen
        }.size
    }

    fun getTiles(rowRange: List<Int>, operation: (Int) -> Unit) {
        for (rowIndex in rowRange) {
            operation(rowIndex)
        }
    }
}
