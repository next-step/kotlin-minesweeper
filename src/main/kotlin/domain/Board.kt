package domain

class Board private constructor(val rows: List<Row>) {

    private val allCells = rows.flatMap { it.cells }

    val mineCount = allCells.count { it is Mine }

    val cellCount = allCells.size

    fun mineCount(cell: Cell): Int =
        allCells.count { it.isAdjacentTo(cell) && it is Mine }

    fun open(cell: Cell): GameStatus {
        cell.open()

        when (cell) {
            is Mine -> return GameStatus.LOST
            is Empty -> getClearCells(cell).forEach { it.openAll() }
        }

        return if (isClear()) GameStatus.WIN
        else GameStatus.CONTINUE
    }

    private fun getClearCells(cell: Cell, clearCells: MutableList<Cell> = mutableListOf()): List<Cell> {
        cell
            .emptyNeighbors()
            .filter { mineCount(it) == 0 }
            .filter { !clearCells.contains(it) }
            .forEach {
                clearCells.add(it)
                getClearCells(it, clearCells)
            }

        return clearCells
    }

    private fun Cell.emptyNeighbors(): List<Cell> =
        allCells.filter { it.isAdjacentTo(this) }.filterIsInstance<Empty>()

    private fun isClear(): Boolean =
        allCells.filterIsInstance<Empty>().all { it.opened }

    private fun Cell.openAll() {
        open()
        emptyNeighbors().forEach { it.open() }
    }

    companion object {

        fun of(rows: List<Row>): Board {
            require(rows.isNotEmpty()) { "적어도 하나의 row 가 필요합니다" }
            require(rows.map { it.size }.distinct().size == 1) { "모든 row 의 크기는 같아야 합니다" }

            return Board(rows)
        }
    }
}
