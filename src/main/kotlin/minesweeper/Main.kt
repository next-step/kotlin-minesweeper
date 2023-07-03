package minesweeper

import minesweeper.domain.Board
import minesweeper.ui.BoardHeightReader
import minesweeper.ui.BoardMineCountReader
import minesweeper.ui.BoardPrinter
import minesweeper.ui.BoardWidthReader

fun main() {
    val height = BoardHeightReader.read()
    val width = BoardWidthReader.read()
    val countOfMine = BoardMineCountReader.read()

    val board = Board.of(height, width, countOfMine)

    BoardPrinter.print(board)
}
