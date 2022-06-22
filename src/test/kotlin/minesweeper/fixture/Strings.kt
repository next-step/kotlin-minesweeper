package minesweeper.fixture

import minesweeper.model.board.CellBuilder
import minesweeper.model.board.coordinate.BoardArea
import minesweeper.model.board.coordinate.Position

fun List<String>.toCellBuilder(): CellBuilder {
    val rowCount = this.count()
    val columnCount = this.maxOf { it.length }
    val isMine: (Position) -> Boolean = {
        this[it.row][it.column] == '*'
    }
    return CellBuilder(BoardArea.of(rowCount, columnCount), isMine)
}
