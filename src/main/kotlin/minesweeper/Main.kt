package minesweeper

import minesweeper.domain.Board
import minesweeper.ui.BoardHeightReader
import minesweeper.ui.BoardPrinter

fun main() {
    val height = BoardHeightReader.read()
    val width = BoardHeightReader.read()
    val countOfMine = BoardHeightReader.read()

    val board = Board.of(height, width, countOfMine)

    BoardPrinter.print(board)
}
