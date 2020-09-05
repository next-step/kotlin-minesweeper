package model

import java.lang.RuntimeException

class Game(val width: Int, val height: Int) {
    val cells: MutableList<Cell> = mutableListOf()
    val mines: List<Cell>
        get() = this.countMap.filter { it.value == Value.MINE }
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
        val cell = findCell(position)
        unDefineToCount(cell)
        if (cell.isMine()) return throw RuntimeException("패배했습니다.")
        if (!cell.isZero()) return
        Position.getAroundPositions(position, width, height).forEach {
            openMap(it)
        }
    }

    fun winCheck(): Boolean {
        return cells.filter { it.value == Value.UNDEFINE }.size == mines.size && countMap.containsAll(mines)
    }

    private fun findCell(position: Position): Cell {
        return countMap.find { it.match(position) } ?: throw IllegalArgumentException("해당하는 칸이 없습니다.")
    }

    private fun aroundCellAddCount(position: Position) {
        Position.getAroundPositions(position, width, height).forEach {
            findCell(it)?.addCount()
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
