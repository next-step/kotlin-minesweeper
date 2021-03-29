package minesweeper.domain

class ProtoCells(private val protoCells: List<ProtoCell>) {
    fun updateSide(matrix: Matrix) {
        for ((index, cell) in protoCells.withIndex()) {
            cell.sideCells = matrix.around(index).map { protoCells[it] }
        }
    }

    fun increaseCount() {
        for (cell in protoCells) {
            cell.increaseCount()
        }
    }

    fun cells(matrix: Matrix): List<Cell> {
        val cellStates = protoCells.map { pair(it) }

        cellStates.forEachIndexed { index, (_, list) ->
            list?.addAll(
                matrix.around(index)
                    .map { cellStates[it].first }
                    .filter { it !is CellState.BombCell }
            )
        }

        return cellStates.map { it.first }.map { Cell(it) }
    }

    private fun pair(protoCell: ProtoCell): Pair<CellState, MutableList<CellState>?> {
        with(protoCell) {
            if (bomb) {
                return CellState.BombCell() to null
            }
            if (count > 0) {
                return CellState.BombSideCell(count) to null
            }
        }
        val list = mutableListOf<CellState>()
        return CellState.BlankCell(list) to list
    }
}
