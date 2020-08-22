package model.cell

class Cells(private val cells: MutableList<Cell>) {
    fun get(position: Position): Cell {
        return cells.first { it.position.x == position.x && it.position.y == position.y }
    }

    fun changeNotToMine(count: Int) {
        cells.shuffled().take(count).forEach { changeCell ->
            if (cells.removeIf { targetCell -> targetCell.position == changeCell.position }) {
                cells.add(Cell(MineStatus(!changeCell.mineStatus.isMine), changeCell.position))
            }
        }
    }

    override fun toString(): String {
        return cells.sortedWith(compareBy({ it.position.x }, { it.position.y }))
            .groupBy { it.position.x }.values.joinToString("\n") { it.joinToString(" ") }
    }
}
