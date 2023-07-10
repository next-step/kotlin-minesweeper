package minesweeper

import minesweeper.domain.board.Board
import minesweeper.domain.board.RandomBasedCreationStrategy
import minesweeper.domain.cell.MineCell
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

    val board = Board.of(width, height, countOfMine, RandomBasedCreationStrategy())

    GameMessagePrinter.start()
    while (!board.isClear()) {
        val openPoint: Point = OpenPointReader.read()
        val cell = board.open(openPoint)
        if (cell is MineCell) {
            GameMessagePrinter.openedMine()
        }
        BoardPrinter.print(board)
    }
    GameMessagePrinter.finish()
}
