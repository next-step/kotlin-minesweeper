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

    fun filter(): List<Tile> {
        return row.filter { it.isMine() }
    }
}
