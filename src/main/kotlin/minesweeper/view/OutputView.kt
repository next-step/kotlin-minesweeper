package minesweeper.view

import minesweeper.domain.board.Board
import minesweeper.domain.tile.Mine
import minesweeper.domain.tile.Tile

fun printBoard(height: Int, width: Int, board: Board) {

    val tiles = board.elements.values.toList()
    for (i in (0 until (height * width)) step width) {
        printRow(startIndex = i, width = width, tiles = tiles)
        println()
    }
}

private fun printRow(startIndex: Int, width: Int, tiles: List<Tile>) {
    for (i in (startIndex until (width + startIndex))) {
        print(mapTile(tiles[i]))
    }
}

private fun mapTile(tile: Tile) = if (tile is Mine) {
    "💣"
} else {
    "◻️"
}
