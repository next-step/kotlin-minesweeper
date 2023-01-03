package minesweeper.domain

class MineMap(
    private val mineCells: MutableList<Cell>,
) {
    private val mineCount: Int
        get() = mineCells.count { it is MineCell }

    private val allPositions: List<Position>
        get() = mineCells.map { it.position }

    fun openCell(position: Position): Cell = getCellWithIndex(position).let {
        mineCells[it.index] = it.value.copyWithOpen()
        mineCells[it.index]
    }

    private fun getCellWithIndex(position: Position): IndexedValue<Cell> = mineCells.withIndex()
        .firstOrNull { it.value.position == position }
        ?: throw IllegalStateException("해당 위치의 셀을 찾을 수 없습니다. 위치: (${position.row},${position.column})")

    fun getClosedNearPositions(position: Position) = position.getNearPositions()
        .filter { allPositions.contains(it) }
        .filter { getCell(it).state == CellState.CLOSED }

    private fun getCell(position: Position): Cell =
        mineCells.first { it.position == position }

    fun snapshot() = allPositions.sortedBy { it.column }
        .sortedBy { it.row }
        .map { getCell(it) }

    fun isNotGameClear(): Boolean {
        val closedCells = mineCells.filter { it.state == CellState.CLOSED }
        return !(closedCells.all { it is MineCell } && closedCells.count() == mineCount)
    }

    companion object {
        fun createMineMap(height: Int, width: Int, mineCount: Int): MineMap {
            val positions = Positions(height, width)
            val minePositions = positions.values.shuffled().take(mineCount)
            val mineCells = positions.values
                .map { createCell(minePositions, it) }

            return MineMap(mineCells.toMutableList())
        }

        private fun createCell(
            minePositions: List<Position>,
            position: Position,
        ) = if (minePositions.contains(position)) MineCell(position)
        else CleanCell(position, minePositions)
    }
}
