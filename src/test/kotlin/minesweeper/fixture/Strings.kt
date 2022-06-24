package minesweeper.fixture

import minesweeper.model.board.Board
import minesweeper.model.cell.CellGenerator
import minesweeper.model.coordinate.BoardArea

fun List<String>.toCellGenerator(): CellGenerator {
    val rowCount = this.count()
    val columnCount = this.maxOf { it.length }

    return CellGenerator(BoardArea.of(rowCount, columnCount)) { coordinate, _ ->
        this[coordinate.row][coordinate.column] == '*'
    }
}

fun List<String>.toBoard(): Board {
    val rowCount = this.count()
    val columnCount = this.maxOf { it.length }
    val area = BoardArea.of(rowCount, columnCount)

    return Board(area) { coordinate, _ ->
        this[coordinate.row][coordinate.column] == '*'
    }
}
