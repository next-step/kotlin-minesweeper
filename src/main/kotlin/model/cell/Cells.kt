package model.cell

class Cells(cells: List<Cell>) {
    private val cells: MutableList<Cell> = cells.toMutableList()

    fun createRandomMine(count: Int) {
        cells.shuffled().take(count).forEach { changeCell ->
            if (cells.removeIf { targetCell -> targetCell.position == changeCell.position }) {
                cells.add(Cell(MineType.ZERO, changeCell.position))
            }
        }
    }

    override fun toString(): String {
        return cells.sortedWith(compareBy({ it.position.x }, { it.position.y }))
            .groupBy { it.position.x }.values.joinToString("\n") { it.joinToString(" ") }
    }
}
