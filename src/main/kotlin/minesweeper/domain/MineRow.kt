package minesweeper.domain

class MineRow(width: Number) {

    val rows = List(width.value) { Tile() }
    operator fun get(position: Position): Tile {
        return rows[position.value]
    }

    operator fun get(position: Int): Tile {
        return rows[position]
    }

//    operator fun set(position: Number, tile: Tile) {
//        rows[position.value] = tile
//    }
//
//    operator fun set(position: Int, tile: Tile) {
//        rows[position] = tile
//    }

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
}
