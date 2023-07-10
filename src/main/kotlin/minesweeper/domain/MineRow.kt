package minesweeper.domain

class MineRow(width: NumberValue) {

    private val rows = List(width.value) { Tile() }

    val size: Int = rows.size
    operator fun get(position: Position): Tile {
        return rows[position.value]
    }

    operator fun get(position: Int): Tile {
        return rows[position]
    }

    fun increaseMineCountNearTiles(rangeRow: List<Int>) {
        rangeRow.forEach {
            rows[it].increaseNearMineCount()
        }
    }

    fun sizeOfRemainTiles(): Int = rows.filterNot { it.isOpen }.size

    fun getTiles(rowRange: List<Int>, operation: (Int) -> Unit) {
        rowRange.forEach {
            operation(it)
        }
    }
}
