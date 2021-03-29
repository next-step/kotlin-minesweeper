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

    fun cell() = protoCells.map { it.cell }
}
