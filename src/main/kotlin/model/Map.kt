package model

class Map(val width: Int, val height: Int) {
    val cells: MutableList<Cell> = mutableListOf()
    val mines: List<Cell>
        get() = this.cells.filter { it.value == Value.MINE }

    fun createDefaultMap(width: Int, height: Int) {
        (0 until width).flatMap { x ->
            (0 until height).map { y ->
                this.cells.add(Cell(Position(x, y), Value.UNDEFINE))
            }
        }
    }

    fun createRandomMines(mineCount: Int) {
        this.cells.shuffled().take(mineCount).forEach {
            notMineToMine(it.position)
        }
    }

    fun calculateMineAroundCount() {
        cells.forEach {
            if (it.value == Value.MINE) {
                addCount(it.position)
            }
        }
    }

    private fun addCount(position: Position) {
        Position.getAroundPositions(position, width, height).forEach { aroundPosition ->
            cells.find { it.match(aroundPosition) }?.addCount()
        }
    }

    private fun openMap(cell: Cell): Boolean {
        return true
    }

    fun clickMap(position: Position): Boolean {
        val cell = cells.find { it.match(position) } ?: return false
        return openMap(cell)
    }

    fun winCheck(): Boolean {
        //val cells = cells.filter { }
        return cells.size == mines.size && cells.containsAll(mines)
    }

    private fun notMineToMine(position: Position) {
        if (this.cells.removeIf { it.match(position) }) {
            this.cells.add(Cell(position, Value.MINE))
        }
    }

    override fun toString(): String {
        return this.cells.sortedWith(compareBy({ it.position.x }, { it.position.y })).groupBy { it.position.x }.values.joinToString("\n") { it.joinToString(" ") }
    }
}
