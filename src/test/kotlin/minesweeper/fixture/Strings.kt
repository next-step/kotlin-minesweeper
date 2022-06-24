package minesweeper.fixture

import minesweeper.model.board.Board
import minesweeper.model.cell.CellBuilder
import minesweeper.model.coordinate.BoardArea

fun List<String>.toCellBuilder(): CellBuilder {
    val rowCount = this.count()
    val columnCount = this.maxOf { it.length }

    return CellBuilder(BoardArea.of(rowCount, columnCount)) { coordinate, _ ->
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
