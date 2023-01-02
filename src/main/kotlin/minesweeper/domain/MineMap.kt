package minesweeper.domain

class MineMap(
    private val mineCells: MutableMap<Position, Cell>,
) {
    private val mineCount: Int
        get() = mineCells.values
            .count { it is MineCell }

    fun openCell(position: Position): Cell = getCell(position).run {
        mineCells[position] = this.copyWithOpen()
        this.copyWithOpen()
    }

    private fun getCell(position: Position): Cell =
        mineCells[position]
            ?: throw IllegalStateException("해당 위치의 셀을 찾을 수 없습니다. 위치: (${position.row},${position.column})")


    fun getClosedNearPositions(position: Position) = position.getNearPositions()
        .filter { mineCells.keys.contains(it) }
        .filter { getCell(it).state == CellState.CLOSED }

    fun snapshot() = mineCells.keys.sortedBy { it.column }
        .sortedBy { it.row }
        .map { getCell(it) }

    fun isNotGameClear(): Boolean {
        val closedCells = mineCells.values.filter { it.state == CellState.CLOSED }
        return !(closedCells.all { it is MineCell } && closedCells.count() == mineCount)
    }

    companion object {
        fun createMineMap(height: Int, width: Int, mineCount: Int): MineMap {
            val positions = Positions(height, width)
            val minePositions = positions.values.shuffled().take(mineCount)
            val mineCells: MutableMap<Position, Cell> = positions.values
                .associateWith { createCell(minePositions, it) }
                .toMutableMap()

            return MineMap(mineCells)
        }

        private fun createCell(
            minePositions: List<Position>,
            position: Position,
        ) = if (minePositions.contains(position)) MineCell(position)
        else CleanCell(position, minePositions)
    }
}
