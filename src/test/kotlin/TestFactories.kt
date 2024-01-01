import minesweeper.domain.board.MineBoard
import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.MineCount
import minesweeper.domain.position.Position
import minesweeper.domain.position.Positions

fun Positions(row: Int, column: Int, minePositions: Set<Position>? = null): Positions {
    val allPositions = (0 until row).flatMap { row ->
        (0 until column).map { column ->
            Position(row, column)
        }
    }.toSet()
    val positions = Positions(value = allPositions)
    minePositions?.run { positions.pickMines(this) }
    return positions
}

fun MineBoard(vararg cells: Cell): MineBoard =
    MineBoard(cells.associateBy { it.position })

fun Cell(row: Int, column: Int, mineCount: MineCount = MineCount.ZERO, isOpened: Boolean = false): Cell.Clear =
    Cell.Clear(Position(row, column), mineCount, isOpened)

fun Mine(row: Int, column: Int): Cell.Mine = Cell.Mine(Position(row, column))
