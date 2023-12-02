package minesweeper.domain

class ManualMinePlacementStrategy(private val minePoints: List<Point>) : MinePlacementStrategy {
    override fun placeMine(rows: List<Row>, mineCount: Int): List<Row> {
        val minePointsSet = minePoints.toSet()

        return rows.map { row ->
            Row(row.cells.map { cell ->
                cell.takeIf { it.point !in minePointsSet } ?: cell.copy(state = State.MINE)
            }.toSortedSet())
        }
    }
}
