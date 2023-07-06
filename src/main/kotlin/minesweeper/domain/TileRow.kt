package minesweeper.domain

class TileRow(private val row: MutableList<Tile>) {
    val size: Int
        get() = row.size

    operator fun get(position: Position): Tile {
        return row[position.position]
    }

    operator fun get(position: Int): Tile {
        return row[position]
    }

    operator fun set(position: Position, tile: Tile) {
        row[position.position] = tile
    }

    operator fun set(position: Int, tile: Tile) {
        row[position] = tile
    }

    fun filterMine(): List<Tile> {
        return row.filter { it.isMine() }
    }

    fun sizeOfUnChecked(): Int {
        return row.filter { !it.isCheckedTile }.size
    }

    fun inCreaseMineCountNearTile(rowRange: List<Int>) {
        for(rowIndex in rowRange) {
            val tile = row[rowIndex]
            if(tile !is PlainTile) {
                return
            }
            tile.increaseNearMineCount()
        }
    }

    fun getTiles(rowRange: List<Int>, operation:(Int) -> Unit) {
        for(rowIndex in rowRange) {
            operation(rowIndex)
        }
    }
}
