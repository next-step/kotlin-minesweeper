import minesweeper.domain.board.Positions
import minesweeper.domain.cell.Position

fun Positions(row: Int, column: Int, minePositions: Set<Position>? = null): Positions {
    val allPositions = (0 until row).flatMap { row ->
        (0 until column).map { column ->
            Position(row, column)
        }
    }.toSet()
    minePositions?.forEach { require(it in allPositions) }
    val positions = Positions(value = allPositions)
    minePositions?.run { positions.pickMines(this) }
    return positions
}
