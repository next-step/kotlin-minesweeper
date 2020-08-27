package model.cell

class Cells(cells: List<Cell>) {
    private val cells: MutableList<Cell> = cells.toMutableList()

    fun createRandomMines(count: Int) {
        cells.shuffled().take(count).forEach { changeCell ->
            if (cells.removeIf { targetCell -> targetCell.position == changeCell.position }) {
                cells.add(Cell(MineType.MINE, changeCell.position))
            }
        }
    }

    fun checkMines(maxX: Int, maxY: Int) {
        cells.forEach {
            if (it.mineType == MineType.MINE) {
                aroundCellAddCount(it.position, maxX, maxY)
            }
        }
    }

    private fun aroundCellAddCount(position: Position, maxX: Int, maxY: Int) {
        val operand = listOf(-1, 0, 1)
        val x = position.x
        val y = position.y
        operand.flatMap { operandX ->
            operand.map { operandY ->
                val targetX = x + operandX
                val targetY = y + operandY
                if ((targetX in 0 until maxX && targetY in 0 until maxY))
                    cells.firstOrNull() { it.match(Position(targetX, targetY)) && it.mineType == MineType.NOT_MINE }
                        ?.addCount()
            }
        }
    }

    override fun toString(): String {
        return cells.sortedWith(compareBy({ it.position.x }, { it.position.y }))
            .groupBy { it.position.y }.values.joinToString("\n") { it.joinToString(" ") }
    }
}
