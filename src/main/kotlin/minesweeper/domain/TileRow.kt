package minesweeper.domain

class TileRow(private val row: MutableList<Tile>) : List<Tile> by row {
    operator fun set(position: Int, tile: MineTile) {
        row[position] = tile
    }
}
