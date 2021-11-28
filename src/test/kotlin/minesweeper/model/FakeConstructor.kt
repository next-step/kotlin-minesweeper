package minesweeper.model

fun Board(
    width: Int,
    height: Int,
    mineCount: Int = 0
) = Board.shuffled(Width.valueOf(width), Height.valueOf(height), MineCount.valueOf(mineCount))

fun Cells(vararg positions: Pair<Int, Int>): Cells = positions.map { (row, column) ->
    Cell.Blank(Row(row), Column(column))
}.let(::Cells)
