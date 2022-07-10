package domain

class Board private constructor(val rows: List<Row>) {

    private val allCells: List<Cell> = rows.flatMap { it.cells }

    val mineCount: Int
        get() = allCells.count { it is Mine }

    val cellCount: Int
        get() = allCells.size

    fun getCell(coordinate: Coordinate): Cell =
        allCells.find { it.equalsBy(coordinate) } ?: throw IllegalArgumentException("존재하지 않는 cell 입니다")

    fun mineCount(cell: Cell): Int =
        allCells.count { it.isAdjacentTo(cell) && it is Mine }

    fun open(cell: Cell): GameStatus {
        cell.open()

        when (cell) {
            is Mine -> return GameStatus.LOST
            is Empty -> getZeroMineCells(cell).forEach { it.openWithNeighbors() }
        }

        return if (isClear()) GameStatus.WIN
        else GameStatus.CONTINUE
    }

    private fun getZeroMineCells(cell: Cell, clearCells: MutableList<Cell> = mutableListOf()): List<Cell> {
        cell
            .emptyNeighbors()
            .filter { mineCount(it) == 0 }
            .filter { !clearCells.contains(it) }
            .forEach {
                clearCells.add(it)
                getZeroMineCells(it, clearCells)
            }

        return clearCells
    }

    private fun Cell.emptyNeighbors(): List<Cell> =
        allCells.filter { it.isAdjacentTo(this) }.filterIsInstance<Empty>()

    private fun isClear(): Boolean =
        allCells.filterIsInstance<Empty>().all { it.opened }

    private fun Cell.openWithNeighbors() {
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
