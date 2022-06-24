package minesweeper.fixture

import minesweeper.model.board.Board
import minesweeper.model.cell.CellBuilder
import minesweeper.model.coordinate.BoardArea
import minesweeper.model.coordinate.Coordinate

fun List<String>.toCellBuilder(): CellBuilder {
    val rowCount = this.count()
    val columnCount = this.maxOf { it.length }
    val isMine: (Coordinate, Coordinate) -> Boolean = { coordinate, _ ->
        this[coordinate.row][coordinate.column] == '*'
    }
    return object : CellBuilder(BoardArea.of(rowCount, columnCount)) {
        override fun isMineCell(coordinate: Coordinate, firstClickCoordinate: Coordinate) =
            isMine(coordinate, firstClickCoordinate)
    }
}

fun List<String>.toBoard(): Board {
    val rowCount = this.count()
    val columnCount = this.maxOf { it.length }
    val isMine: (Coordinate, Coordinate) -> Boolean = { coordinate, _ ->
        this[coordinate.row][coordinate.column] == '*'
    }
    val area = BoardArea.of(rowCount, columnCount)
    return Board.build(area, isMine)
}
