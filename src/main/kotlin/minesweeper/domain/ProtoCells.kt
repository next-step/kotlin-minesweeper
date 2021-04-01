package minesweeper.domain

class ProtoCells(private val protoCells: List<ProtoCell>, private val matrix: Matrix) {
    fun cells(): List<Cell> {
        updateSide()
        increaseCount()

        val (cells, links) = protoCells.map { it.cellAndLink() }.unzip()

        return cells.apply {
            update(links)
        }
    }

    private fun updateSide() {
        for ((index, cell) in protoCells.withIndex()) {
            cell.sideCells = matrix.around(index).map { protoCells[it] }
        }
    }

    private fun increaseCount() {
        for (cell in protoCells) {
            cell.increaseCount()
        }
    }

    private fun List<Cell>.update(links: List<ProtoCell.Links>) {
        links.mapIndexed { index, link ->
            link to matrix.around(index)
                .map { this[it] }
                .filterNot { it.bomb }
        }.forEach { (links, cells) -> links.save(cells) }
    }
}
