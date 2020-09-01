package model

class Map(val width: Int, val height: Int) {
    val cells: MutableList<Cell> = mutableListOf()
    val mines: List<Cell>
        get() = this.countMap.filter { it.value == Value.MINE }
    var lose: Boolean = false
        private set
    private val countMap: MutableList<Cell> = mutableListOf()


    fun createDefaultMap(width: Int, height: Int) {
        (0 until width).flatMap { x ->
            (0 until height).map { y ->
                this.cells.add(Cell(Position(x, y), Value.UNDEFINE))
            }
        }
    }

    fun createCountMap() {
        cells.forEach {
            countMap.add(Cell(it.position, Value.ZERO))
        }
    }

    fun createRandomMines(mineCount: Int) {
        this.countMap.shuffled().take(mineCount).forEach {
            notMineToMine(it.position)
        }
    }

    fun calculateCount() {
        countMap.forEach {
            if (it.isMine()) {
                aroundCellAddCount(it.position)
            }
        }
    }

    fun openMap(position: Position) {
        val cell = findCell(countMap, position)
        unDefineToCount(cell)
        if (cell.value == Value.MINE) {
            lose = true
            return
        }
        if (cell.value != Value.ZERO) return
        Position.getAroundPositions(position, width, height).forEach {
            openMap(it)
        }
    }

    fun winCheck(): Boolean {
        return cells.filter { it.value == Value.UNDEFINE }.size == mines.size && countMap.containsAll(mines)
    }

    private fun findCell(targetCells: MutableList<Cell>, position: Position): Cell {
        return targetCells.find { it.match(position) } ?: throw IllegalArgumentException("해당하는 칸이 없습니다.")
    }

    private fun aroundCellAddCount(position: Position) {
        Position.getAroundPositions(position, width, height).forEach {
            findCell(countMap, it)?.addCount()
        }
    }

    private fun notMineToMine(position: Position) {
        if (this.countMap.removeIf { it.match(position) }) {
            this.countMap.add(Cell(position, Value.MINE))
        }
    }

    private fun unDefineToCount(cell: Cell) {
        if (this.cells.removeIf { it.match(cell.position) }) {
            this.cells.add(Cell(cell.position, cell.value))
        }
    }

    override fun toString(): String {
        return this.cells.sortedWith(compareBy({ it.position.x }, { it.position.y })).groupBy { it.position.x }.values.joinToString("\n") { it.joinToString(" ") }
    }
}
