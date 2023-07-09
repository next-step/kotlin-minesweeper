package minesweeper.view

import minesweeper.domain.board.GameBoard
import minesweeper.domain.board.Tile

object OutputView {
    private const val mineSymbol = "*"
    private const val nonMineSymbol = "C"
    fun printMineBoard(board: GameBoard) {
        for (tiles in board.board) {
            printTiles(tiles)
            println()
        }
    }

    private fun printTiles(tiles: List<Tile>) {
        for (tile in tiles) {
            val symbol = if (tile.isMineActive()) mineSymbol else tile.getNeighborMineCount()
            print("$symbol ")
        }
    }
}
