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
        val (cells, links) = protoCells.map { it.cellAndLink() }.unzip()

        links.update(matrix, cells)

        return cells
    }

    private fun List<ProtoCell.Links>.update(matrix: Matrix, cells: List<Cell>) {
        mapIndexed { index, links ->
            links to matrix.around(index)
                .map { cells[it] }
                .filterNot { it.bomb }
        }.forEach { (links, cells) -> links.save(cells) }
    }
}
