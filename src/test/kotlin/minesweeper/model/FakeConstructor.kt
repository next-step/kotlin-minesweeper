package minesweeper.model

fun Board(width: Int, height: Int): Board = Board.create(Width.valueOf(width), Height.valueOf(height))

fun Board(
    width: Int,
    height: Int,
    mineCount: Int
): Board {
    val size = width * height
    if (size == 0) return Board.EMPTY
    val positions = Position.list(Width.valueOf(width), Height.valueOf(height)).shuffled()
    val cells = positions.mapIndexed { index, position ->
        if (index < mineCount) {
            Cell.Mine(position)
        } else {
            Cell.zero(position)
        }
    }
    return Board(Cells(cells))
}

fun Cells(vararg positions: Pair<Int, Int>): Cells = positions.map { (row, column) ->
    Cell.zero(Position(row, column))
}.let(::Cells)

fun Position(row: Int, column: Int): Position = Position.of(Row(row), Column(column))

operator fun Cells.get(position: Pair<Int, Int>): Cell? = get(Position.of(Row(position.first), Column(position.second)))
