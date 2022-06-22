package minesweeper.fixture

import minesweeper.model.board.Board
import minesweeper.model.cell.CellBuilder
import minesweeper.model.coordinate.BoardArea
import minesweeper.model.coordinate.Position

fun List<String>.toCellBuilder(): CellBuilder {
    val rowCount = this.count()
    val columnCount = this.maxOf { it.length }
    val isMine: (Position) -> Boolean = {
        this[it.row][it.column] == '*'
    }
    return CellBuilder(BoardArea.of(rowCount, columnCount), isMine)
}

fun List<String>.toBoard(): Board {
    val rowCount = this.count()
    val columnCount = this.maxOf { it.length }
    val isMine: (Position) -> Boolean = {
        this[it.row][it.column] == '*'
    }
    val area = BoardArea.of(rowCount, columnCount)
    return Board.build(area, isMine)
}
