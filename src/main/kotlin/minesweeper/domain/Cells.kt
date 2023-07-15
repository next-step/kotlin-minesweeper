package minesweeper.domain

@JvmInline
value class Cells(
    private val values: List<List<Cell>> = emptyList(),
) : List<List<Cell>> by values {
    val height: Int
        get() = values.size

    val width: Int
        get() = values.first().size

    init {
        validateHeightIsPositive()
        validateWidthIsPositive()
        validateSameWidth()
    }

    private fun validateHeightIsPositive() {
        require(height > 0) { "높이는 0보다 커야 합니다." }
    }

    private fun validateWidthIsPositive() {
        require(width > 0) { "너비는 0보다 커야 합니다." }
    }

    private fun validateSameWidth() {
        require(values.all { it.size == width }) { "너비가 일정하지 않습니다." }
    }

    fun allOpened(): Boolean {
        return values.all { row ->
            row.filterIsInstance<Normal>().all { cell -> cell.isOpened }
        }
    }

    fun open(position: Position): Cell {
        val cell = values[position].open()
        if (cell is Normal && cell.isAdjacentMineCountZero()) {
            cell.openAdjacentNormalCells()
        }

        return cell
    }

    private fun Cell.openAdjacentNormalCells() {
        val adjacentNormalCells = getAdjacentNormalCells(this)
        adjacentNormalCells.openIfAdjacentMineCountNotZero()
        adjacentNormalCells.openIfAdjacentMineCountZero()
    }

    private fun getAdjacentNormalCells(cell: Cell): List<Normal> {
        return cell.position.getAdjacentPositions(thresholdWidth = width, thresholdHeight = height)
            .filter { get(it) is Normal }
            .map { get(it) as Normal }
            .filter { !it.isOpened }
    }

    private fun List<Normal>.openIfAdjacentMineCountNotZero() {
        filter { !it.isAdjacentMineCountZero() }
            .forEach { it.open() }
    }

    private fun List<Normal>.openIfAdjacentMineCountZero() {
        filter { it.isAdjacentMineCountZero() }
            .forEach { open(it.position) }
    }

    operator fun get(position: Position): Cell {
        return values[position]
    }

    operator fun List<List<Cell>>.get(position: Position): Cell {
        return values[position.y][position.x]
    }
}
