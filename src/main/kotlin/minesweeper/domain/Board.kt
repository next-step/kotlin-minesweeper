package minesweeper.domain

@JvmInline
value class Board(val cells: List<Cell>) {

    fun remainMineCount() = cells.count { it is Cell.Mine }

    fun groupByColumn(): Map<Int, List<Cell>> = cells.groupBy(keySelector = { it.coordinate.y })
}
