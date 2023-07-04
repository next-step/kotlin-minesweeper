package minesweeper.domain

class Board(
    val cells: List<Cells>,
) {
    init {
        validateHeightIsPositive()
        validateWidthIsPositive()
        validateSameWidth()

        calculateAdjacentMineCount()
    }

    private fun validateHeightIsPositive() {
        require(cells.isNotEmpty()) { "높이는 0보다 커야 합니다." }
    }

    private fun validateWidthIsPositive() {
        require(cells.first().size > 0) { "너비는 0보다 커야 합니다." }
    }

    private fun validateSameWidth() {
        val width = cells.first().size
        require(cells.all { it.values.size == width }) { "너비가 일정하지 않습니다." }
    }

    private fun calculateAdjacentMineCount() {
        val positionsOfMineCell = getPositionsOfMineCell()

        positionsOfMineCell.forEach { position ->
            position.increaseAdjacentMineCount()
        }
    }

    private fun getPositionsOfMineCell(): List<Position> {
        return cells.flatMapIndexed { y, row ->
            row.mapIndexedNotNull { x, cell ->
                if (cell is Mine) Position(x, y) else null
            }
        }
    }

    private fun Position.increaseAdjacentMineCount() {
        getAdjacentPositions(thresholdWidth = cells.first().size, thresholdHeight = cells.size)
            .map { position -> getCell(position) }
            .filterIsInstance<Normal>()
            .forEach { it.increaseAdjacentMineCount() }
    }

    private fun getCell(position: Position): Cell {
        return cells[position.y][position.x]
    }

    companion object {
        fun of(
            height: PositiveInt,
            width: PositiveInt,
            mineCount: PositiveInt,
        ): Board {
            val normals: Cells = Cells.normal((height * width - mineCount).value)
            val mines: Cells = Cells.mine(mineCount.value)

            return Board(
                cells = (normals + mines)
                    .shuffled()
                    .chunked(width.value)
                    .map { Cells(it) },
            )
        }
    }
}
