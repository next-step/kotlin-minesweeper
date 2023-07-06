package minesweeper.domain

class BoardGenerator(
    val width: PositiveInt,
    val height: PositiveInt,
    val mineCount: PositiveInt,
) {
    fun generate(
        minePositions: List<Position> = PositionGenerator.generatePositionsRandomly(width, height, mineCount),
    ): Board {
        validateMineCount(minePositions)

        return Board(generateCells(minePositions))
    }

    private fun validateMineCount(minePositions: List<Position>) {
        require(minePositions.size == mineCount.value) { "지뢰의 개수는 ${mineCount.value}개여야 합니다." }
    }

    private fun generateCells(minePositions: List<Position>): List<Cells> {
        val defaultCells = generateAllNormalCells()

        minePositions.forEach { position ->
            defaultCells[position.y][position.x] = Mine(position)
            position.increaseAdjacentMineCount(defaultCells)
        }

        return defaultCells.map { Cells(it) }
    }

    private fun generateAllNormalCells(): List<MutableList<Cell>> {
        return (0 until width.value * height.value)
            .map { position ->
                Normal(Position(position % width.value, position / width.value))
            }
            .chunked(width.value)
            .map { it.toMutableList() }
    }

    private fun Position.increaseAdjacentMineCount(cells: List<List<Cell>>) {
        getAdjacentPositions(thresholdWidth = width.value, thresholdHeight = height.value)
            .map { position -> cells[position] }
            .filterIsInstance<Normal>()
            .forEach { it.increaseAdjacentMineCount() }
    }

    private operator fun List<List<Cell>>.get(position: Position): Cell {
        return this[position.y][position.x]
    }
}
