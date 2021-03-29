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
        val cellStateAndLink = protoCells.map { cellStateAndLink(it) }

        cellStateAndLink.forEachIndexed { index, (_, link) ->
            link?.addAll(
                matrix.around(index)
                    .map { cellStateAndLink[it].first }
                    .filter { it !is CellState.Bomb }
            )
        }

        return cellStateAndLink.map { it.first }.map { Cell(it) }
    }

    private fun cellStateAndLink(protoCell: ProtoCell): Pair<CellState, MutableList<CellState>?> {
        with(protoCell) {
            if (bomb) {
                return CellState.Bomb() to null
            }
            if (count > 0) {
                return CellState.BombSide(count) to null
            }
        }
        val link = mutableListOf<CellState>()
        return CellState.Blank(link) to link
    }
}
