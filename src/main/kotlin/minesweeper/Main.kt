package minesweeper

import minesweeper.domain.board.Board
import minesweeper.domain.board.RandomBasedCreationStrategy
import minesweeper.domain.point.Point
import minesweeper.ui.BoardHeightReader
import minesweeper.ui.BoardMineCountReader
import minesweeper.ui.BoardPrinter
import minesweeper.ui.BoardWidthReader
import minesweeper.ui.GameMessagePrinter
import minesweeper.ui.OpenPointReader

fun main() {
    val height = BoardHeightReader.read()
    val width = BoardWidthReader.read()
    val countOfMine = BoardMineCountReader.read()

    val board = Board(RandomBasedCreationStrategy(height, width, countOfMine))

    GameMessagePrinter.start()
    while (!board.isClear()) {
        val openPoint: Point = OpenPointReader.read()
        board.open(openPoint)
        BoardPrinter.print(board)
    }
    println("Clear!")
}
