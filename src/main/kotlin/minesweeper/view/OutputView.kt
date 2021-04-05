package minesweeper.view

import minesweeper.domain.board.Board
import minesweeper.domain.tile.Blank
import minesweeper.domain.tile.Mine
import minesweeper.domain.tile.Tile

fun printBoard(width: Int, board: Board) {

    val tiles = board.elements.values.toList()

    for (i in (tiles.indices) step width) {
        printRow(startIndex = i, width = width, tiles = tiles)
        println()
    }
}

private fun printRow(startIndex: Int, width: Int, tiles: List<Tile>) {
    for (i in (startIndex until (width + startIndex))) {
        print(mapTile(tiles[i]))
    }
}

private fun mapTile(tile: Tile) = when (tile) {
    is Mine -> "💣"
    is Blank -> when (tile.nearByMineCount) {
        1 -> "1️⃣"
        2 -> "2️⃣"
        3 -> "3️⃣"
        4 -> "4️⃣"
        5 -> "5️⃣"
        6 -> "6️⃣"
        7 -> "7️⃣"
        8 -> "8️⃣"
        else -> "◻️"
    }
    else -> throw IllegalArgumentException()
}
