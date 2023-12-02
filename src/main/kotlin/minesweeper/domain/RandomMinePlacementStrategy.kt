package minesweeper.domain

class RandomMinePlacementStrategy : MinePlacementStrategy {
    override fun placeMine(rows: List<Row>, mineCount: Int): List<Row> {
        val allCells = rows.flatMap { it.cells }.toMutableList()
        val mineIndices = allCells.indices.shuffled().take(mineCount).toSet()
        mineIndices.forEach { index ->
            allCells[index] = allCells[index].copy(state = State.MINE)
        }

        return rows.map { row ->
            val updatedCells = row.cells.map { cell ->
                allCells.find { it.point == cell.point } ?: cell
            }.toSortedSet()
            Row(updatedCells)
        }
    }
}
