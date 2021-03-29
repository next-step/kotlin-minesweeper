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

    fun cell(): List<Cell> = protoCells.map { it.cell }

    fun cells(matrix: Matrix): List<Cell> {
        val cellStates = protoCells.map { pair(it) }

        cellStates.forEachIndexed { index, (cellState, list) ->
            if (cellState is CellState.BlankCell) {
                list.addAll(matrix.around(index).map { cellStates[it].first })
            }
        }

        return cellStates.map { it.first }.map { CellWithState(it) }
    }

    private fun pair(protoCell: ProtoCell): Pair<CellState, MutableList<CellState>> {
        with(protoCell) {
            if (bomb) {
                return CellState.BombCell() to mutableListOf()
            }
            if (count > 0) {
                return CellState.BombSideCell(count) to mutableListOf()
            }
        }
        val list = mutableListOf<CellState>()
        return CellState.BlankCell(list) to list
    }
}
