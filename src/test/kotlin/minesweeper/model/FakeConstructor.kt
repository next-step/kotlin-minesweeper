package minesweeper.model

fun Board(width: Int, height: Int): Board = Board.create(Width.valueOf(width), Height.valueOf(height))

fun Board(
    width: Int,
    height: Int,
    mineCount: Int
) = Board.shuffled(Width.valueOf(width), Height.valueOf(height), MineCount.valueOf(mineCount))

fun Cells(vararg positions: Pair<Int, Int>): Cells = positions.map { (row, column) ->
    Cell.Zero(Position(row, column))
}.let(::Cells)

fun Position(row: Int, column: Int): Position = Position(Row(row), Column(column))

operator fun Cells.get(position: Pair<Int, Int>): Cell? = get(Position(Row(position.first), Column(position.second)))
